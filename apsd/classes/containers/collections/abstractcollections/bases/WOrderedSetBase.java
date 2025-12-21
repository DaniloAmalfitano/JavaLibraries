package apsd.classes.containers.collections.abstractcollections.bases;

 import apsd.interfaces.containers.base.IterableContainer;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.OrderedSet;
 import apsd.interfaces.containers.collections.SortedChain;


/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<Data extends Comparable<? super Data>, Chn extends SortedChain<Data>> extends WSetBase<Data, Chn> implements OrderedSet<Data> {

  public WOrderedSetBase(){
        super();
  }

  public WOrderedSetBase(Chn chn){
        super(chn);
  }

    public WOrderedSetBase(TraversableContainer<Data> con){
            super(con);
    }

    public WOrderedSetBase(Chn chn, TraversableContainer<Data> con){
            super(chn, con);
    }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

    @Override
    public boolean IsEqual(IterableContainer<Data> con) {
        if (con == null || !this.Size().equals(con.Size())) return false;
        return !con.TraverseForward(elm -> !Exists(elm));
    }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */
    @Override
    public Data Min(){
        return chn.Min();
    }
    @Override
    public Data Max(){
        return chn.Max();
    }
    @Override
    public void RemoveMin(){
        chn.MinNRemove();
    }
    @Override
    public void RemoveMax() {
        chn.MaxNRemove();
    }
    @Override
    public Data MinNRemove(){
        return chn.MinNRemove();
    }
    @Override
    public Data MaxNRemove() {
        return chn.MaxNRemove();
    }
    @Override
    public Data Predecessor(Data dat) {
        return chn.Predecessor(dat);
    }
    @Override
    public void RemovePredecessor(Data dat) {
        chn.RemovePredecessor(dat);
    }
    @Override
    public void RemoveSuccessor(Data dat) {
        chn.RemoveSuccessor(dat);
    }
    @Override
    public Data Successor(Data dat) {
        return chn.Successor(dat);
    }
    @Override
    public Data PredecessorNRemove(Data dat) {
        return chn.PredecessorNRemove(dat);
    }
    @Override
    public Data SuccessorNRemove(Data dat) {
        return chn.SuccessorNRemove(dat);
    }
}
