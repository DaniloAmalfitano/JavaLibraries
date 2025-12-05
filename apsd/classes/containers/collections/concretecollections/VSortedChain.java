package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.SortedChain;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> { // Must extend VChainBase and implements SortedChain

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
    public boolean Insert(Data data) {
        if (data == null) {
            return false;
        }
        Natural pred = SearchPredecessor(data);
        Natural pos = (pred == null) ? Natural.ZERO : pred.Increment();
        if(pos !=null) {
            vec.InsertAt(data, pos);
            return true;
        }
        return false;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Chain                            */
    /* ************************************************************************ */

    // Set-like: do not insert if already present
    @Override
    public boolean InsertIfAbsent(Data data) {
        if (data == null) {
            return false;
        }
        Natural pred = SearchPredecessor(data);
        Natural pos = (pred == null) ? Natural.ZERO : pred.Increment();
        if(pos !=null) {
            if (pos.compareTo(vec.Size()) < 0) {
                Data currentElement = vec.GetAt(pos);
                if (currentElement != null && data.compareTo(currentElement) == 0) {
                    return false;
                }
            }
            vec.InsertAt(data, pos);
            return true;
        }
        return false;
    }

    @Override
    public void RemoveOccurrences(Data data) {
        if (data == null) {
            return;
        }

        Natural pos = Natural.ZERO;
        // find first element >= data
        while (pos.compareTo(vec.Size()) < 0) {
            Data currentElement = vec.GetAt(pos);
            if (currentElement == null) {
                return; // no more data
            }
            int cmp = data.compareTo(currentElement);
            if (cmp <= 0) {
                break;
            }
            pos = pos.Increment();
        }

        // remove consecutive == data
        while (pos.compareTo(vec.Size()) < 0) {
            Data currentElement = vec.GetAt(pos);
            if (currentElement == null) {
                break;
            }
            if (data.compareTo(currentElement) != 0) {
                break;
            }
            vec.RemoveAt(pos);
        }
    }
}
