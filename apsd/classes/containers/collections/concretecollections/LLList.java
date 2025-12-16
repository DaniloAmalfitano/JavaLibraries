package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
 import apsd.classes.containers.collections.concretecollections.bases.LLNode;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.MutableSequence;


/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data>{

    public LLList(){
    super();
  }

    public LLList(TraversableContainer<Data> con){
        super(con);
    }

    protected LLList(long size, LLNode<Data> head, LLNode<Data> tail){
    super(size, head, tail);
  }

    @Override
    protected LLChainBase<Data> NewChain(long size, LLNode<Data> headref, LLNode<Data> tailref) {
        return new LLList<>(size, headref, tailref);
    }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

    @Override
    public MutableForwardIterator<Data> FIterator() {
        return new ListFIterator();
    }

    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return new ListBIterator();
    }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

    @Override
    public void SetAt(Data dat, Natural index) {
        long idx = ExcIfOutOfBound(index);
        LLNode<Data> node = headref.Get();
        long i = 0;
        while (i < idx && node != null) {
            node = node.GetNext().Get();
            i++;
        }
        if (node == null) return;
        node.Set(dat);
    }

    @Override
    public void SetFirst(Data dat) {
        if(dat == null) return;
        if (headref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
        headref.Get().Set(dat);
    }

    @Override
    public void SetLast(Data dat) {
        if (tailref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
        tailref.Get().Set(dat);
    }

    @Override
    public MutableSequence<Data> SubSequence(Natural startindex, Natural endindex) {
        long startIndex = ExcIfOutOfBound(startindex);
        long endIndex = ExcIfOutOfBound(endindex);
        if (startIndex > endIndex) throw new IndexOutOfBoundsException("Start index greater than end index");
        LLList<Data> subList = new LLList<>();
        LLNode<Data> cur = headref.Get();
        long index = 0;
        while (cur != null && index < endIndex) {
            if (index >= startIndex) {
                subList.InsertLast(cur.Get());
            }
            cur = cur.GetNext().Get();
            index++;
        }
        return subList;
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */

    @Override
    public void InsertAt(Data dat, Natural index) {
        if(dat == null) return;
        long idx = index.ToLong();
        if (idx > size.ToLong()) throw new IndexOutOfBoundsException("Index: " + idx);
        if (idx == 0) {
            InsertFirst(dat);
        } else if (idx == size.ToLong()) {
            InsertLast(dat);
        } else {
            LLNode<Data> node = headref.Get();
            long i = 0;
            while (i < idx - 1 && node != null) {
                node = node.GetNext().Get();
                i++;
            }
            if(node == null) throw new IllegalStateException("Internal list structure corrupted");
            LLNode<Data> newNode = new LLNode<>(dat);
            newNode.SetNext(node.GetNext().Get());
            node.SetNext(newNode);
            size.Increment();
        }
    }

    @Override
    public void InsertFirst(Data dat) {
        if(dat == null) return;
        LLNode<Data> newNode = new LLNode<>(dat);
        newNode.SetNext(headref.Get());
        headref.Set(newNode);
        if (tailref.Get() == null) {
            tailref.Set(newNode);
        }
        size.Increment();
    }

    @Override
    public void InsertLast(Data dat) {
        if(dat == null) return;
        LLNode<Data> newNode = new LLNode<>(dat);
        if (tailref.Get() == null) {
            headref.Set(newNode);
            tailref.Set(newNode);
        } else {
            tailref.Get().SetNext(newNode);
            tailref.Set(newNode);
        }
        size.Increment();
    }
    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */
}




