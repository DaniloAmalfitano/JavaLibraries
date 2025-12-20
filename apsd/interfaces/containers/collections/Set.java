package apsd.interfaces.containers.collections;


import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data>{

  default void Union(Set<Data> set){
    if(set != null){
      set.TraverseForward(elem -> {
        if(!this.Exists(elem))
          Insert(elem);
        return false;
      });
    }
  }

  default void Difference(Set<Data> set){
    if(set != null){
      Filter(elem -> !set.Exists(elem));
    }
  }

  default void Intersection(Set<Data> set){
    if(set == null){
        this.Clear();
    }
    else{
      Filter(set::Exists);
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  @Override
  default boolean IsEqual(IterableContainer<Data> con) {
    if (con == null || !this.Size().equals(con.Size())) return false;
    return this.TraverseForward(con::Exists);
  }
}
