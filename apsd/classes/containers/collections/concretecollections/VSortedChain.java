package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.SortedChain;
 import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> {

  public VSortedChain(){
        super();
  }

  public VSortedChain(VSortedChain<Data> chn){
        super(chn.vec);
  }

  public VSortedChain(TraversableContainer<Data> con){
        super(con);
  }

  protected VSortedChain(DynVector<Data> vec){
        super(vec);
  }

    @Override
    protected VChainBase<Data> NewChain(DynVector<Data> vec) {
        return new VSortedChain<>(vec);
    }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

    @Override
    public boolean Insert(Data dat) {
        if (dat == null) return false;
        Natural pred = SearchPredecessor(dat);
        Natural pos = (pred == null) ? Natural.ZERO : pred.Increment();
        if(pos !=null) {
            vec.InsertAt(dat, pos);
            return true;
        }
        return false;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Chain                            */
    /* ************************************************************************ */

    @Override
    public boolean InsertIfAbsent(Data dat) {
        if (dat == null) return false;
        Natural pred = SearchPredecessor(dat);
        Natural pos = (pred == null) ? Natural.ZERO : pred.Increment();
        if(pos !=null) {
            if (pos.compareTo(vec.Size()) < 0) {
                Data currentElement = vec.GetAt(pos);
                if (currentElement != null && dat.compareTo(currentElement) == 0) {
                    return false;
                }
            }
            vec.InsertAt(dat, pos);
            return true;
        }
        return false;
    }

    @Override
    public void RemoveOccurrences(Data dat) {
        if (dat == null) return;
        Natural occurrenceIndex = Search(dat);
        if (occurrenceIndex == null) return;
        Natural successorIndex = SearchSuccessor(dat);
        long start = occurrenceIndex.ToLong();
        long end = (successorIndex == null) ? vec.Size().ToLong() : successorIndex.ToLong();
        long count = end - start;
        if (count > 0)
            vec.ShiftLeft(Natural.Of(start), Natural.Of(count));
    }

}
