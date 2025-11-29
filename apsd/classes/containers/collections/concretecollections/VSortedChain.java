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
        Natural pos = Natural.ZERO;
        while (pos.compareTo(vec.Size()) < 0 && vec.GetAt(pos).compareTo(data) < 0) {
            pos = pos.Increment();
        }
        vec.InsertAt(data, pos);
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

    @Override
    public boolean InsertIfAbsent(Data data) {
        if (data == null) {
            return false;
        }
        Natural pos = Natural.ZERO;
        while (pos.compareTo(vec.Size()) < 0 && vec.GetAt(pos).compareTo(data) < 0) {
            pos = pos.Increment();
        }
        if (pos.compareTo(vec.Size()) < 0 && vec.GetAt(pos).compareTo(data) == 0) {
            return false;
        }
        vec.InsertAt(data, pos);
        return true;
    }
    @Override
    public void RemoveOccurrences(Data data) {
        if (data == null) {
            return;
        }
        Natural pos = Natural.ZERO;
        while (pos.compareTo(vec.Size()) < 0 && vec.GetAt(pos).compareTo(data) < 0) {
            pos = pos.Increment();
        }
        while (pos.compareTo(vec.Size()) < 0 && vec.GetAt(pos).compareTo(data) == 0) {
            vec.RemoveAt(pos);
        }
    }
}
