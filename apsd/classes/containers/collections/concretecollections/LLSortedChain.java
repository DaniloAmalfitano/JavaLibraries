package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
 import apsd.classes.containers.collections.concretecollections.bases.LLNode;
 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.SortedChain;
 import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data> implements SortedChain<Data>{ // Must extend LLChainBase and implement SortedChain

  public LLSortedChain(){
      super();
  }

  public LLSortedChain(LLSortedChain<Data> chn){
        super(chn);
  }

  public LLSortedChain(TraversableContainer<Data> con){
        super(con);
  }

  protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail){
        super(size, head, tail);
  }

  // NewChain
    @Override
    protected LLChainBase<Data> newChain(long size, LLNode<Data> headref, LLNode<Data> tailref) {
        return new LLSortedChain<>(size, headref, tailref);
    }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

  // ...//PredFind rifallo usando un iteratore, è più semplice
    protected LLNode<Data> PredFind(Data dat){
        if(dat == null) return null;
        Box<LLNode<Data>> curr = headref;
        long lenght = Size().ToLong();
        LLNode<Data> pred = null;
        while(lenght > 0) {
            long newLenght = (lenght - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long idx = 0; idx < newLenght; idx++) {
                next = next.Get().GetNext();
            }
            if (next.Get().Get().compareTo(dat) < 0) {
                pred = next.Get();
                curr = next.Get().GetNext();
                lenght = lenght - newLenght - 1;
            } else {
                lenght = newLenght;
            }
        }
        return pred;
    }

    public LLNode<Data> PredPredFind(Data dat) {
        if (dat == null) return null;
        ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
        long len = Size().ToLong();
        LLNode<Data> predPred = null;
        while (len > 1) {
            long newLen = (len - 1) / 2;
            ForwardIterator<Box<LLNode<Data>>> next = new ListFRefIterator((ListFRefIterator) itr);
            next.Next(newLen - 1);
            LLNode<Data> temp = next.GetCurrent().Get();
            if (next.GetCurrent().Get().Get().compareTo(dat) < 0) {
                predPred = temp;
                itr = next;
                len = len - newLen;
            } else {
                len = newLen;
            }
        }
        return predPred;
    }

    public LLNode<Data> PredSuccFind(Data dat){
        if(dat == null) return null;
        ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
        long len = Size().ToLong();
        LLNode<Data> predSucc = null;
        while(len > 1){
            long newLen = len / 2;
            ForwardIterator<Box<LLNode<Data>>> next = new ListFRefIterator((ListFRefIterator) itr);
            next.Next(newLen - 1);
            LLNode<Data> temp = next.GetCurrent().Get();
            if(next.GetCurrent().Get().Get().compareTo(dat) <= 0){
                predSucc = temp;
                itr = next;
                len = len - newLen;
            } else {
                len = newLen;
            }
        }
        return predSucc;
    }



  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

    @Override
    public boolean Insert(Data dat){
        if(dat == null) return false;
        LLNode<Data> pred = PredFind(dat);
        Box<LLNode<Data>> curr = (pred == null) ? headref : pred.GetNext();
        LLNode<Data> node = curr.Get();
        LLNode<Data> newNode = new LLNode<>(dat, node);
        curr.Set(newNode);
        if(tailref.Get() == pred){
            tailref.Set(newNode);
        }
        size.Increment();
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

    @Override
    public boolean Remove(Data dat){
        if(dat == null) return false;
        LLNode<Data> pred = PredFind(dat);
        Box<LLNode<Data>> curr = (pred == null) ? headref : pred.GetNext();
        if(curr.IsNull() || !curr.Get().Get().equals(dat)){
            return false;
        }
        curr.Set(curr.Get().GetNext().Get());
        if(tailref.Get() == curr.Get()){
            tailref.Set(pred);
        }
        size.Decrement();
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

    @Override
    public Natural Search(Data dat){
        if(dat == null) return null;
        long low = 0;
        long high = Size().ToLong() - 1;
        while(low <= high){
            long mid = low + (high - low) / 2;
            Data elem = GetAt(Natural.Of(mid));
            int cmp = elem.compareTo(dat);

            if(cmp == 0){
                return Natural.Of(mid);
            } else if(cmp < 0){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }


  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
  /* ************************************************************************ */

    @Override
    public Natural SearchPredecessor(Data data) {
        if (data == null || headref.IsNull()) return null;
        Box<LLNode<Data>> curr = headref;
        long len = size.ToLong();
        long index = -1L;

        while (len > 0 && !curr.IsNull()) {
            long step = (len - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long i = 0; i < step; i++) {
                if (next.IsNull()) break;
                LLNode<Data> node = next.Get();
                if (node == null) { next = new Box<>(); break; }
                next = node.GetNext();
            }

            if (next.IsNull()) break;
            LLNode<Data> node = next.Get();
            if (node == null) break;

            int cmp = node.Get().compareTo(data);
            if (cmp < 0) {
                Box<LLNode<Data>> afterBox = node.GetNext();
                curr = afterBox.IsNull() ? new Box<>() : afterBox;
                index += step + 1;
                len = len - (step + 1);
            } else len = step;
        }

        return (index >= 0) ? Natural.Of(index) : null;
    }

    @Override
    public Natural SearchSuccessor(Data data) {
        if (data == null || headref.IsNull()) return null;
        Box<LLNode<Data>> curr = headref;
        long len = size.ToLong();
        long baseIndex = 0L;
        long result = -1L;

        while (len > 0 && !curr.IsNull()) {
            long step = (len - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long i = 0; i < step; i++) {
                if (next.IsNull()) break;
                LLNode<Data> node = next.Get();
                if (node == null) { next = new Box<>(); break; }
                next = node.GetNext();
            }

            if (next.IsNull()) break;
            LLNode<Data> node = next.Get();
            if (node == null) break;

            int cmp = node.Get().compareTo(data);
            if (cmp > 0) {
                result = baseIndex + step;
                len = step;
            } else {
                Box<LLNode<Data>> afterBox = node.GetNext();
                curr = afterBox.IsNull() ? new Box<>() : afterBox;
                baseIndex += step + 1;
                len = len - (step + 1);
            }
        }

        return (result >= 0) ? Natural.Of(result) : null;
    }
  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */
  @Override
  public boolean InsertIfAbsent(Data dat) {
      if(dat == null) return false;
      Natural index = Search(dat);
      if(index == null){
          Insert(dat);
          return true;
      } else {
          return false;
      }
  }


  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

    @Override
    public void RemoveOccurrences(Data dat){
        if(dat == null) return;
        LLNode<Data> pred = PredFind(dat);
        Box<LLNode<Data>> curr = (pred == null) ? headref : pred.GetNext();
        while(!curr.IsNull() && curr.Get().Get().equals(dat)){
            curr.Set(curr.Get().GetNext().Get());
            size.Decrement();
        }
        if(pred == null){
            headref.Set(curr.Get());
        } else {
            pred.SetNext(curr.Get());
        }
        if(curr.IsNull()){
            tailref.Set(pred);
        }
    }
}
