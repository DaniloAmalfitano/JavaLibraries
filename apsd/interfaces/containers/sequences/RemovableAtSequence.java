package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data>{ // Must extend Sequence

  // RemoveAt
    default void RemoveAt(Natural index){
        if(index.ToLong() < 0 || index.ToLong() >= Size().ToLong()){
            throw new IndexOutOfBoundsException("Index out of bounds: " + index.ToLong() + "; Size: " + Size().ToLong() + "!");
        }
        AtNRemove(Natural.Of(index.ToLong()));
    }
  // AtNRemove
    Data AtNRemove (Natural index);

  // RemoveFirst
    default void RemoveFirst(){
      if(IsEmpty()){
        throw new IndexOutOfBoundsException("Cannot remove from empty sequence!");
      }
      AtNRemove(Natural.ZERO);
    }
  // FirstNRemove
    default Data FirstNRemove(){
      if(IsEmpty()){
        throw new IndexOutOfBoundsException("Cannot remove from empty sequence!");
      }
      return AtNRemove(Natural.ZERO);
    }

  // RemoveLast
    default void RemoveLast(){
      AtNRemove(IsEmpty() ? Natural.ZERO : Size().Decrement());
    }
  // LastNRemove
    default Data LastNRemove(){
      return AtNRemove(IsEmpty() ? Natural.ZERO : Size().Decrement());
    }
}
