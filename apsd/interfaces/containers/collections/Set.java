package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data>{ // Must extend Collection

  // Union
    default void Union(Set<Data> other){
      if(other != null){
        other.TraverseForward(elem -> {
          if(!this.Exists(elem))
            Insert(elem);
          return false;
        });
      }
    }

  // Difference
    default void Difference(Set<Data> other){
      if(other == null) return;
      other.TraverseForward(elem -> {
          this.Remove(elem);
          return false;
      });
  }

  // Intersection
    default void Intersection(Set<Data> other){
      if(other != null){
        this.TraverseForward(elem -> {
          if(!other.Exists(elem))
            Remove(elem);
          return false;
        });
      }
    }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
    @Override
    default boolean IsEqual(IterableContainer<Data> other) {
        return other != null && this.Size().equals(other.Size()) && this.TraverseForward(elem -> !other.Exists(elem));
    }
}
