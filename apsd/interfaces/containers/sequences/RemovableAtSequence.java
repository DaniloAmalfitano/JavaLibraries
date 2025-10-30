package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data>{ // Must extend Sequence

  // RemoveAt
    default void RemoveAt(long index){
        long idx = ExcIfOutOfBound(Natural.Of(index));
        AtNRemove(Natural.Of(idx));
    }
  // AtNRemove
    Data AtNRemove (Natural index);

  // RemoveFirst
    default void RemoveFirst(){
      AtNRemove(Natural.ZERO);
    }
  // FirstNRemove
    default Data FirstNRemove(){
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
