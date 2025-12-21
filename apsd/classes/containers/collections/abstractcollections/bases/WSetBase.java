package apsd.classes.containers.collections.abstractcollections.bases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.collections.Set;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data> {

  protected Chn chn;

  public WSetBase(){
      ChainAlloc();
  }
  public WSetBase(Chn chn){
      ChainAlloc();
      if (chn != null) {
          chn.TraverseForward(dat -> {
              if (dat != null) this.chn.InsertIfAbsent(dat);
              return false;
          });
      }
  }
    public WSetBase(TraversableContainer<Data> con){
        ChainAlloc();
        con.TraverseForward(dat -> {
            if (dat != null) {
                chn.InsertIfAbsent(dat);
            }
            return false;
        });
    }

    public WSetBase(Chn chn, TraversableContainer<Data> con) {
        ChainAlloc();
        if (chn != null) {
            chn.TraverseForward(dat -> {
                if (dat != null) this.chn.InsertIfAbsent(dat);
                return false;
            });
        }
        if (con != null) {
            con.TraverseForward(dat -> {
                if (dat != null) this.chn.InsertIfAbsent(dat);
                return false;
            });
        }
    }

  abstract protected void ChainAlloc();

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
    public Natural Size() {
        return chn.Size();
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    @Override
    public void Clear() {
        chn.Clear();
    }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

    @Override
    public boolean Insert(Data dat) {
        if(dat == null) return false;
        return chn.InsertIfAbsent(dat);
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

    @Override
    public boolean Remove(Data dat) {
        return chn.Remove(dat);
    }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

    @Override
    public ForwardIterator<Data> FIterator() {
        return chn.FIterator();
    }

    @Override
    public BackwardIterator<Data> BIterator() {
        return chn.BIterator();
    }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

    @Override
    public boolean Filter(Predicate<Data> pred) {
        return chn.Filter(pred);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

    @Override
    public void Intersection(Set<Data> other) {
        chn.Intersection(other);
    }
}
