package apsd.classes.containers.collections.concretecollections.bases;

 import apsd.classes.containers.sequences.DynCircularVector;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.DynVector;
 import apsd.interfaces.containers.sequences.Sequence;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<Data> implements Chain<Data>{

  protected final DynVector<Data> vec;

    protected VChainBase() {
        vec = new DynCircularVector<>();
    }

    public VChainBase(TraversableContainer<Data> con){
        vec = new DynCircularVector<>(con);
    }

    protected VChainBase(DynVector<Data> vec) {
        this.vec = vec;
    }

    abstract protected VChainBase<Data> NewChain(DynVector<Data> vec);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    @Override
    public Natural Size() {
        return vec.Size();
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */


  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

    @Override
    public boolean Remove(Data dat) {
        Natural pos = vec.Search(dat);
        if (dat == null || Size().IsZero() || pos == null) return false;
        vec.ShiftLeft(pos);
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */
    public ForwardIterator<Data> FIterator() {
      return vec.FIterator();
  }
    public BackwardIterator<Data> BIterator() {
        return vec.BIterator();
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

    public Data GetAt(Natural index) {
        return vec.GetAt(index);
    }

    public Sequence<Data> SubSequence(Natural startIdx, Natural endIdx) {
        return vec.SubVector(startIdx, endIdx);
    }
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

    public Data AtNRemove(Natural index) {
      if(Size().IsZero()) return null;
      return vec.AtNRemove(index);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

    @Override
    public void Clear() {
        vec.Clear();
    }

    @Override
    public boolean Filter(Predicate<Data> fun) {
        long del = 0;
        if (fun != null) {
            MutableForwardIterator<Data> itr = vec.FIterator();
            for (; itr.IsValid(); itr.Next()) {
                Data currentData = itr.GetCurrent();
                if (!fun.Apply(currentData)) {
                    del++;
                    itr.SetCurrent(null);
                }
            }
            if (del > 0) {
                itr.Reset();
                MutableForwardIterator<Data> rdr = vec.FIterator();
                for (; rdr.IsValid(); rdr.Next()) {
                    Data dat = rdr.GetCurrent();
                    if (dat != null) {
                        rdr.SetCurrent(null);
                        itr.SetCurrent(dat);
                        itr.Next();
                    }
                }
                vec.Reduce(Natural.Of(del));
            }
        }
        return (del > 0);
    }
}
