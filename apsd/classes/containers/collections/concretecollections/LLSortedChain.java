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

    @Override
    protected LLChainBase<Data> newChain(long size, LLNode<Data> headref, LLNode<Data> tailref) {
        return new LLSortedChain<>(size, headref, tailref);
    }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

    protected LLNode<Data> PredFind(Data dat){
        if(dat == null) return null;
        Box<LLNode<Data>> curr = headref;
        long lenght = Size().ToLong();
        LLNode<Data> pred = null;
        while(lenght > 0) {
            long newLength = (lenght - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long idx = 0; idx < newLength; idx++) {
                next = next.Get().GetNext();
            }
            if (next.Get().Get().compareTo(dat) < 0) {
                pred = next.Get();
                curr = next.Get().GetNext();
                lenght = lenght - newLength - 1;
            } else {
                lenght = newLength;
            }
        }
        return pred;
    }

    public LLNode<Data> PredPredFind(Data dat) {
        if (dat == null) return null;
        Box<LLNode<Data>> curr = headref;
        long length = Size().ToLong();
        LLNode<Data> predPred = null;
        LLNode<Data> pred = null;

        while(length > 0) {
            long newLength = (length - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long idx = 0; idx < newLength; idx++) {
                next = next.Get().GetNext();
            }
            if (next.Get().Get().compareTo(dat) < 0) {
                predPred = pred;
                pred = next.Get();
                curr = next.Get().GetNext();
                length = length - newLength - 1;
            } else {
                length = newLength;
            }
        }
        return predPred;
    }

    public LLNode<Data> SuccFind(Data dat){
        if(dat == null || headref.IsNull()) return null;
        Box<LLNode<Data>> curr = headref;
        long length = Size().ToLong();
        LLNode<Data> succ = null;

        while(length > 0 && !curr.IsNull()) {
            long newLength = (length - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long idx = 0; idx < newLength; idx++) {
                next = next.Get().GetNext();
            }
            if (next.Get().Get().compareTo(dat) > 0) {
                succ = next.Get();
                length = newLength;
            } else {
                curr = next.Get().GetNext();
                length = length - newLength - 1;
            }
        }
        if (!curr.IsNull() && curr.Get() != null && curr.Get().Get().compareTo(dat) > 0) {
            succ = curr.Get();
        }

        return succ;
    }

    public LLNode<Data> PredSuccFind(Data dat){
        if(dat == null || headref.IsNull()) return null;
        Box<LLNode<Data>> curr = headref;
        long length = Size().ToLong();
        LLNode<Data> predSucc = null;
        LLNode<Data> pred = null;

        while(length > 0 && !curr.IsNull()) {
            long newLength = (length - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long idx = 0; idx < newLength; idx++) {
                next = next.Get().GetNext();
            }
            if (next.Get().Get().compareTo(dat) > 0) {
                predSucc = pred;
                length = newLength;
            } else {
                pred = next.Get();
                curr = next.Get().GetNext();
                length = length - newLength - 1;
            }
        }
        if (!curr.IsNull() && curr.Get() != null && curr.Get().Get().compareTo(dat) > 0) {
            predSucc = pred;
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
        if(tailref.Get() == pred)
            tailref.Set(newNode);
        size.Increment();
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

    @Override
    public boolean Remove(Data dat){
        if(dat == null) return false;
        if(headref.IsNull()) return false;
        LLNode<Data> pred = PredFind(dat);
        Box<LLNode<Data>> curr = (pred == null) ? headref : pred.GetNext();

        if(curr.IsNull() || !curr.Get().Get().equals(dat)){
            return false;
        }
        LLNode<Data> nodeToRemove = curr.Get();
        curr.Set(nodeToRemove.GetNext().Get());
        if(tailref.Get() == nodeToRemove){
            tailref.Set(pred);
        }
        size.Decrement();
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */


  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
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
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

    @Override
    public Data Predecessor(Data data) {
        if (data == null || headref.IsNull()) return null;
        LLNode<Data> pred = PredFind(data);
        return pred == null ? null : pred.Get();
    }
    @Override
    public void RemovePredecessor(Data data) {
        LLNode<Data> predNode= PredFind(data);
        LLNode<Data> predPredNode = PredPredFind(data);
        if(predNode == null) return;
        Box<LLNode<Data>> predBox = (predPredNode == null) ? headref : predPredNode.GetNext();
        predBox.Set(predNode.GetNext().Get());
        if(tailref.Get() == predNode){
            tailref.Set(predPredNode);
        }
        size.Decrement();
    }


    public Data PredecessorNRemove(Data data) {
        if (data == null || headref.IsNull()) return null;
        LLNode<Data> predNode = PredFind(data);
        if (predNode == null) return null;
        Data predData = predNode.Get();
        RemovePredecessor(data);
        return predData;
    }

    @Override
    public Data Successor(Data data) {
        if (data == null || headref.IsNull()) return null;
        LLNode<Data> succ = SuccFind(data);
        return succ == null ? null : succ.Get();
    }

    public void RemoveSuccessor(Data data) {
        if (data == null || headref.IsNull()) return;
        LLNode<Data> succNode = SuccFind(data);
        if (succNode == null) return;
        LLNode<Data> predSuccNode = PredSuccFind(data);
        Box<LLNode<Data>> succBox = (predSuccNode == null) ? headref : predSuccNode.GetNext();
        succBox.Set(succNode.GetNext().Get());
        if (tailref.Get() == succNode) {
            tailref.Set(predSuccNode);
        }
        size.Decrement();
    }

    @Override
    public Data SuccessorNRemove(Data data) {
        if (data == null || headref.IsNull()) return null;

        LLNode<Data> succNode = SuccFind(data);
        if (succNode == null) return null;

        Data succData = succNode.Get();
        RemoveSuccessor(data);
        return succData;
    }


  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

    @Override
    public Natural SearchPredecessor(Data data) {
        if (data == null || headref.IsNull()) return null;

        Box<LLNode<Data>> curr = headref;
        long len = size.ToLong();
        long index = -1;

        while (len > 0 && !curr.IsNull()) {
            long step = (len - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long i = 0; i < step; i++) {
                next = next.Get().GetNext();
            }
            int cmp = next.Get().Get().compareTo(data);
            if (cmp < 0) {
                index += step + 1;
                curr = next.Get().GetNext();
                len = len - step - 1;
            } else {
                len = step;
            }
        }
        return (index >= 0) ? Natural.Of(index) : null;
    }

    public Natural SearchSuccessor(Data data) {
        if (data == null || headref.IsNull()) return null;

        Box<LLNode<Data>> curr = headref;
        long len = size.ToLong();
        long index = 0;

        while (len > 0 && !curr.IsNull()) {
            long step = (len - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for (long i = 0; i < step; i++) {
                next = next.Get().GetNext();
            }
            int cmp = next.Get().Get().compareTo(data);
            if (cmp > 0) {
                len = step;
            } else {
                index += step + 1;
                curr = next.Get().GetNext();
                len = len - step - 1;
            }
        }
        return (index < size.ToLong()) ? Natural.Of(index) : null;
    }
    @Override
    public boolean InsertIfAbsent(Data data) {
        if (data == null) return false;
        if (Search(data) != null) return false;
        return Insert(data);
    }

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
