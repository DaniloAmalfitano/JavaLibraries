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
        long len = Size().ToLong();
        LLNode<Data> pred = null;
        while(len > 0){
            long newLen = (len-1) / 2;
            Box<LLNode<Data>> next = curr;
            for(long idx = 0; idx < newLen; idx++){
                next = next.Get().GetNext();
            }
            if(next.Get().Get().compareTo(dat) < 0){
                pred = next.Get();
                curr = next.Get().GetNext();
                len = len - newLen - 1;
            } else {
                len = newLen;
            }
        }
        return pred;
    }

    protected LLNode<Data> PredPredFind(Data dat){
        if(dat == null) return null;
        Box<LLNode<Data>> curr = headref;
        long len = Size().ToLong();
        LLNode<Data> pred = null;
        LLNode<Data> predpred = null;
        while(len > 0){
            long newLen = (len-1) / 2;
            Box<LLNode<Data>> next = curr;
            for(long idx = 0; idx < newLen; idx++){
                next = next.Get().GetNext();
            }
            if(next.Get().Get().compareTo(dat) < 0){
                predpred = pred;
                pred = next.Get();
                curr = next.Get().GetNext();
                len = len - newLen - 1;
            } else {
                len = newLen;
            }
        }
        return predpred;
    }
    protected LLNode<Data> PredSuccFind(Data dat){
        if(dat == null) return null;
        Box<LLNode<Data>> curr = headref;
        long len = Size().ToLong();
        LLNode<Data> pred = null;
        LLNode<Data> succ = null;
        while(len > 0){
            long newLen = (len-1) / 2;
            Box<LLNode<Data>> next = curr;
            for(long idx = 0; idx < newLen; idx++){
                next = next.Get().GetNext();
            }
            if(next.Get().Get().compareTo(dat) < 0){
                pred = next.Get();
                curr = next.Get().GetNext();
                len = len - newLen - 1;
            } else {
                succ = next.Get();
                len = newLen;
            }
        }
        return succ;
    }



  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

    @Override
    public boolean Insert(Data dat){
        if(dat == null) return false;
        LLNode<Data> pred = PredFind(dat);
        Box<LLNode<Data>> curr = (pred == null) ? headref : pred.GetNext();
        LLNode<Data> newNode = new LLNode<>(dat, curr.Get());
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
        Box<LLNode<Data>> curr = headref;
        long lenght = Size().ToLong();
        long index = 0;
        while(lenght > 0) {
            lenght = lenght / 2;
            Box<LLNode<Data>> next = curr;
            for (long idx = 0; idx < lenght; idx++) {
                next = next.Get().GetNext();
            }
            Data elem = next.Get().Get();
            if (elem.compareTo(dat) == 0) {
                return Natural.Of(index + lenght);
            } else if (elem.compareTo(dat) < 0) {
                curr = next;
                index += lenght;
            }
        }
        return null;
    }

  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
  /* ************************************************************************ */

    /*@Override
    public Natural SearchPredecessor(Data dat){
        if(dat == null) return null;
        LLNode<Data> pred = PredFind(dat);
        if(pred == null) return null;
        Box<LLNode<Data>> curr = headref;
        long index = 0;
        while(curr.Get() != pred){
            curr = curr.Get().GetNext();
            index++;
        }
        return Natural.Of(index);
    }*/
    @Override
    public Natural SearchPredecessor(Data dat){
        if(dat == null) return null;
        Box<LLNode<Data>> curr = headref;
        long len = Size().ToLong();
        long index = -1;
        while(len > 0){
            long newLen = (len - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for(long i = 0; i < newLen; i++){
                next = next.Get().GetNext();
            }
            Data elem = next.Get().Get();
            if(elem.compareTo(dat) < 0){
                curr = next.Get().GetNext();
                index += newLen + 1;
                len = len - newLen -1;
            } else {
                len = newLen;
            }
        }
        return (index == -1) ? null : Natural.Of(index);
    }

    @Override
    public Natural SearchSuccessor(Data dat){
        if(dat == null) return null;
        Box<LLNode<Data>> curr = headref;
        long len = Size().ToLong();
        long index = len;
        while(len > 0){
            long newLen = (len - 1) / 2;
            Box<LLNode<Data>> next = curr;
            for(long i = 0; i < newLen; i++){
                next = next.Get().GetNext();
            }
            Data elem = next.Get().Get();
            if(elem.compareTo(dat) <= 0){
                curr = next.Get().GetNext();
                len = len - newLen -1;
            } else {
                len = newLen;
                index += newLen + 1;
            }
        }
        return (index == size.ToLong()) ? null : Natural.Of(index);
    }


  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */
    @Override
    public boolean InsertIfAbsent(Data dat){
        if(dat == null) return false;
        LLNode<Data> pred = PredFind(dat);
        Box<LLNode<Data>> curr = (pred == null) ? headref : pred.GetNext();
        if(!curr.IsNull() && curr.Get().Get().equals(dat)){
            return false;
        }
        LLNode<Data> newNode = new LLNode<>(dat, curr.Get());
        curr.Set(newNode);
        if(tailref.Get() == pred){
            tailref.Set(newNode);
        }
        size.Increment();
        return true;
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
