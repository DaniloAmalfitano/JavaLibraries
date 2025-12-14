package apsd.interfaces.containers.collections;


import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data>{

  default void Union(Set<Data> other){
    if(other != null){
      other.TraverseForward(elem -> {
        if(!this.Exists(elem))
          Insert(elem);
        return false;
      });
    }
  }

  default void Difference(Set<Data> other){
    if(other != null){
      Filter(elem -> !other.Exists(elem));
    }
  }

  default void Intersection(Set<Data> set){
    Filter(set::Exists);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  default boolean IsEqual(IterableContainer<Data> con) {
    return con != null && this.Size().equals(con.Size()) && this.TraverseForward(elem -> !con.Exists(elem));
  }
}
