package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container{

  boolean Remove(Data dat);

  default boolean RemoveAll(TraversableContainer<Data> container) {
      return container.FoldForward((d, acc) ->
              this.Remove(d) && acc, true);
  }

  default boolean RemoveSome(TraversableContainer<Data> container){
        return container.FoldForward((d, acc) -> {
            if (this.Remove(d)) return true;
            return acc;
            }, false);
  }
}
