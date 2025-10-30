package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container{ // Must extend Container

  boolean Insert(Data data);

  default boolean InsertAll(TraversableContainer<Data> container) {
    final Box<Boolean> box = new Box<>(true);
    if(container != null) {
      container.TraverseForward(data -> {
        box.Set(box.Get() && this.Insert(data));
        return false;
      });
    }
    return box.Get();
  }

  default boolean InsertSome(TraversableContainer<Data> container) {
    final Box<Boolean> box = new Box<>(false);
    if(container != null) {
      container.TraverseForward(data -> {
        box.Set(box.Get() || this.Insert(data));
        return false;
      });
    }
    return box.Get();
  }
}
