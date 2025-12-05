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
    public boolean IsEqual(IterableContainer<Data> con){
        return chn.IsEqual(con);
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
    public Data Predecessor(Data data) {
        return chn.Predecessor(data);
    }
    @Override
    public void RemovePredecessor(Data data) {
        chn.RemovePredecessor(data);
    }
    @Override
    public void RemoveSuccessor(Data data) {
        chn.RemoveSuccessor(data);
    }
    @Override
    public Data Successor(Data data) {
        return chn.Successor(data);
    }
    @Override
    public Data PredecessorNRemove(Data data) {
        return chn.PredecessorNRemove(data);
    }
    @Override
    public Data SuccessorNRemove(Data data) {
        return chn.SuccessorNRemove(data);
    }
}
