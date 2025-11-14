package apsd.classes.containers.collections.concretecollections.bases;

 import apsd.classes.containers.sequences.Vector;
 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.MutableNatural;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.RemovableContainer;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.Sequence;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> implements Chain<Data> { // Must implement Chain

    protected final MutableNatural size = new MutableNatural();
    protected final Box<LLNode<Data>> headref = new Box<>();
    protected final Box<LLNode<Data>> tailref = new Box<>();

    public LLChainBase() {

    }

    public LLChainBase(TraversableContainer<Data> con) { //Se il dato è null non va bene, modificare, questa deve essere una collezione, ma gli passo un cointainer
        size.Assign(con.Size());
        final Box<Boolean> first = new Box<>(true);
        con.TraverseForward(dat -> {
            LLNode<Data> node = new LLNode<>(dat);
            if (first.Get()) {
                headref.Set(node);
                first.Set(false);
            } else {
                tailref.Get().SetNext(node);
            }
            tailref.Set(node);
            return false;
        });
    }
    public LLChainBase(long size, LLNode<Data> head, LLNode<Data> tail) {
        this.size.Assign(size);
        this.headref.Set(head);
        this.tailref.Set(tail);
    }

    abstract protected LLChainBase<Data> newChain(long size, LLNode<Data> headref, LLNode<Data> tailref);

    /* ************************************************************************ */
    /* Specific member functions from LLChainBase                               */
    /* ************************************************************************ */



    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    public Natural Size() {
        return size.ToNatural();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    public void Clear() {
        headref.Set(null);
        tailref.Set(null);
        size.Assign(0);
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    protected class ListFRefIterator implements ForwardIterator<Box<LLNode<Data>>> {
        protected Box<LLNode<Data>> cur;

        protected ListFRefIterator() {
            this.cur = headref;
        }
        protected ListFRefIterator(ListFRefIterator itr) {
            this.cur = itr.cur;
        }

        @Override
        public Box<LLNode<Data>> DataNNext() {
            if(!IsValid()) {
                throw new IllegalStateException("Iterator is not valid.");
            }
            Box<LLNode<Data>> oldCur = cur;
            cur = cur.Get().GetNext();
            return oldCur;
        }

        @Override
        public boolean IsValid() {
            return !cur.IsNull();
        }

        @Override
        public void Reset() {
            cur = headref;
        }

        @Override
        public Box<LLNode<Data>> GetCurrent() {
            if(!IsValid()) throw new IllegalStateException("Iterator is not valid.");
            return cur;
        }
        @Override
        public void Next() {
            if(!IsValid()) throw new IllegalStateException("Iterator is not valid.");
            cur = cur.Get().GetNext();
        }
    }

    protected ForwardIterator<Box<LLNode<Data>>> FRefIterator() {;
        return new ListFRefIterator();
    }
    protected class ListBRefIterator implements BackwardIterator<Box<LLNode<Data>>> {
        protected long cur = -1L;
        protected Vector<Box<LLNode<Data>>> arr = null;

        protected ListBRefIterator() {
            Reset();
        }
        protected ListBRefIterator(ListBRefIterator itr) {
            cur = itr.cur;
            arr = new Vector<>(itr.arr);
        }


        @Override
        public boolean IsValid() {
            return (cur >= 0L && cur < size.ToLong());
        }

        @Override
        public void Reset() {
            cur = -1L;
            if(Size().IsZero())
                arr = null;
            else{
                arr = new Vector<>(Size());
                for(Box<LLNode<Data>> ref = headref; !ref.IsNull(); ref = ref.Get().GetNext()){
                    arr.SetAt(ref, Natural.Of(++cur));
                }
            }
        }

        @Override
        public Box<LLNode<Data>> GetCurrent() {
            if(!IsValid()) throw new IllegalStateException("Iterator is not valid.");
            return arr.GetAt(Natural.Of(cur));
        }

        @Override
        public Box<LLNode<Data>> DataNPrev() {
            if(!IsValid()) throw new IllegalStateException("Iterator is not valid.");
            return arr.GetAt(Natural.Of(cur--));
        }

        @Override
        public void Prev() {
            if(!IsValid()) throw new IllegalStateException("Iterator is not valid.");
            cur--;
        }
    }
    protected BackwardIterator<Box<LLNode<Data>>> BRefIterator() {;
        return new ListBRefIterator();
    }

    protected class ListFIterator implements MutableForwardIterator<Data> {
        protected final ForwardIterator<Box<LLNode<Data>>> itr;

        protected ListFIterator() {
            itr = FRefIterator();
        }
        protected ListFIterator(ListFIterator itr) {
            this.itr = itr.itr;
        }

        @Override
        public Data DataNNext() {
            if(!IsValid()) {;
                throw new IllegalStateException("Iterator is not valid.");
            }
            Box<LLNode<Data>> oldCur = itr.GetCurrent();
            itr.Next();
            return oldCur.Get().Get();
        }

        @Override
        public boolean IsValid() {
            return itr.IsValid();
        }

        @Override
        public void Reset() {
            itr.Reset();
        }

        @Override
        public Data GetCurrent() {
            if(!IsValid()) throw new IllegalStateException("Iterator is not valid.");
            return itr.GetCurrent().Get().Get();
        }
        @Override
        public void Next() {
            itr.Next();
        }

        @Override
        public void SetCurrent(Data data) {
            if(data == null) return;
            itr.GetCurrent().Get().Set(data);
        }
    }
    @Override
    public ForwardIterator<Data> FIterator() {;
        return new ListFIterator();
    }

    protected class ListBIterator implements MutableBackwardIterator<Data> {
        protected final BackwardIterator<Box<LLNode<Data>>> itr;

        protected ListBIterator() {
            itr = BRefIterator();
        }
        protected ListBIterator(ListBIterator itr) {
            this.itr = itr.itr;
        }

        @Override
        public Data DataNPrev() {
            if(!IsValid()) {
                throw new IllegalStateException("Iterator is not valid.");
            }
            Box<LLNode<Data>> oldCur = itr.GetCurrent();
            itr.Prev();
            return oldCur.Get().Get();
        }

        @Override
        public boolean IsValid() {
                return itr.IsValid();
        }

        @Override
        public void Reset() {
            itr.Reset();
        }

        @Override
        public Data GetCurrent() {
            if(!IsValid()) throw new IllegalStateException("Iterator is not valid.");
            return itr.GetCurrent().Get().Get();
        }
        @Override
        public void Prev() {
            itr.Prev();
        }

        @Override
        public void SetCurrent(Data data) {
            if(data == null) return;
            itr.GetCurrent().Get().Set(data);
        }
    }
    @Override
    public BackwardIterator<Data> BIterator() {;
        return new ListBIterator();
    }
   @Override
   public boolean Remove(Data dat) {
     if (dat == null) return false;
     final Box<LLNode<Data>> prd = new Box<>();
     return FRefIterator().ForEachForward(cur -> {
       LLNode<Data> node = cur.Get();
       if (node.Get().equals(dat)) {
         cur.Set(node.GetNext().Get());
         if (tailref.Get() == node) { tailref.Set(prd.Get()); }
         size.Decrement();
         return true;
       }
       prd.Set(node);
       return false;
     });
   }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */
    //Per i GetFirst/Last tailred.Get().Get() Dereferenzi due volte
  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ... SubSequence
     @Override
    public Data GetAt(Natural pos) {
        long idx = ExcIfOutOfBound(pos);
        Box<LLNode<Data>> curr = headref;
        for (long i = 0; i < idx; i++) {
            curr = curr.Get().GetNext();
        }
        return curr.Get().Get();
    }

    public Sequence<Data> SubSequence(Natural start, Natural end) {
        long s = ExcIfOutOfBound(start);
        long e = ExcIfOutOfBound(end);
        if (s > e) {
            throw new IllegalArgumentException("Start index cannot be greater than end index.");
        }
        LLNode<Data> newHead = null;
        LLNode<Data> newTail = null;
        LLNode<Data> curr = headref.Get();
        for (long i = 0; i <= e; i++) {
            if (i >= s) {
                LLNode<Data> newNode = new LLNode<>(curr.Get());
                if (newHead == null) {
                    newHead = newNode;
                } else {
                    newTail.SetNext(newNode);
                }
                newTail = newNode;
            }
            curr = curr.GetNext().Get();
        }
        return newChain(e - s + 1, newHead, newTail);
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...//AtNRemove
    @Override
    public Data AtNRemove(Natural pos) {
        long idx = ExcIfOutOfBound(pos);
        Box<LLNode<Data>> curr = headref;
        Box<LLNode<Data>> prev = null;
        for (long i = 0; i < idx; i++) {
            prev = curr;
            curr = curr.Get().GetNext();
        }
        Data removedData = curr.Get().Get();
        if (prev == null) { // Rimozione in testa
            headref.Set(curr.Get().GetNext().Get());
            if (headref.IsNull()) {
                tailref.Set(null);
            }
        } else {
            prev.Get().SetNext(curr.Get());
            if (curr.Get() == tailref.Get()) {
                tailref.Set(prev.Get());
            }
        }
        size.Decrement();
        return removedData;
    }

    /*Verifico se la posizione esista, se no restituisco un eccezione*, vedo se devo inserire in testa, coda o mezzo
        if(idx== Size.ToLong()-1) return lastNremove
        altrimenti forwarditerator
        box<LLNode<Data>> curr  ecc
        RemoveFirst
        verifico se è null, ritorno
        verifico se la testa è uguale alla coda, metto entrambi a null
        altrimenti headRef.Set(Headref.Get().GetNext().Get())
     */

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ... La filter è sbagliata //prd = node -> prd=cur.Get();
    @Override
    public boolean Filter(Predicate<Data> pred) {
        if (pred == null) return false;
        long oldSize = size.ToLong();

        ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
        Box<LLNode<Data>> prev = new Box<>();

        while (itr.IsValid()) {
            Box<LLNode<Data>> curBox = itr.GetCurrent();
            LLNode<Data> node = curBox.Get();
            if (pred.Apply(node.Get())) {
                prev.Set(node);
                itr.Next();
            } else {
                curBox.Set(node.GetNext() == null ? null : node.GetNext().Get());
                if (tailref.Get() == node) {
                    tailref.Set(prev.Get());
                }
                size.Decrement();
            }
        }
        return oldSize != size.ToLong();
    }
}
