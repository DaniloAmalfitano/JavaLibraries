package apsd.classes.containers.collections.abstractcollections.bases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.collections.Set;
 import apsd.interfaces.containers.iterators.BackwardIterator;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.traits.Predicate;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data> { // Must implement Set; Chn must extend Chain

  protected Chn chn;

  public WSetBase(){
      ChainAlloc();
  }
  public WSetBase(Chn chn){
      this.chn = chn;
  }
  public WSetBase(TraversableContainer<Data> con){
      ChainAlloc();
      con.TraverseForward(dat -> {
          chn.InsertIfAbsent(dat);
          return false;
      });
  }
  public WSetBase(Chn chn, TraversableContainer<Data> con){
      this.chn = chn;
    con.TraverseForward(dat -> {
        this.chn.InsertIfAbsent(dat);
        return false;
    });
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

    /*@Override
    public boolean Insert(Data data) {
        return chn.InsertIfAbsent(data);
    }*/
    @Override
    public boolean Insert(Data data) {
        return chn.InsertIfAbsent(data);
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

    @Override
    public boolean Remove(Data data) {
        return chn.Remove(data);
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
    /*
    public Set<Data> Intersection(Set<Data> set1, Set<Data> set2) {
        this.chn.Clear();
        ForwardIterator<Data> it1 = set1.FIterator();
        while (it1.IsValid()) {
            Data d = it1.DataNNext();
            boolean found = false;
            ForwardIterator<Data> it2 = set2.FIterator();
            while (it2.IsValid()) {
                Data e = it2.DataNNext();
                if ((d == null && e == null) || (d != null && d.equals(e))) {
                    found = true;
                    break;
                }
            }
            if (found) {
                this.chn.InsertIfAbsent(d);
            }
        }
        return this;
    }*/ //Non ho fatto io questo metodo, non me ne prendo i meriti

    /*@Override
    public void Intersection(Set<Data> other){
      if(other != null){
        this.chn.Clear();
        other.TraverseForward(elem -> {
          if(other.Exists(elem))
            this.chn.InsertIfAbsent(elem);
          return false;
        });
      }
    }*///Questo l'ho fatto io, infatti fa cagare
    @Override
    public void Intersection(Set<Data> other) {
        if (other == null) return;
        chn.Filter(other::Exists);
    }

}
