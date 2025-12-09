package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container{

  boolean Insert(Data data);

  default boolean InsertAll(TraversableContainer<Data> con) {
    final Box<Boolean> box = new Box<>(true);
    if(con != null) {
      con.TraverseForward(data -> {
        box.Set(box.Get() && this.Insert(data));
        return false;
      });
    }
    return box.Get();
  }

  default boolean InsertSome(TraversableContainer<Data> con) {
    final Box<Boolean> box = new Box<>(false);
    if(con != null) {
      con.TraverseForward(data -> {
        box.Set(box.Get() || this.Insert(data));
        return false;
      });
    }
    return box.Get();
  }
}
