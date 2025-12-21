package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data>{
    default void RemoveAt(Natural index){
        long idx = ExcIfOutOfBound(index);
        AtNRemove(Natural.Of(idx));
    }
    Data AtNRemove (Natural index);

    default void RemoveFirst(){
      if(IsEmpty()) return;
      AtNRemove(Natural.ZERO);
    }

    default Data FirstNRemove(){
      if(IsEmpty()) return null;
      return AtNRemove(Natural.ZERO);
    }

    default void RemoveLast(){
      if(IsEmpty()) return;
      AtNRemove(Size().Decrement());
    }

    default Data LastNRemove(){
      if(IsEmpty()) return null;
      return AtNRemove(Size().Decrement());
    }
}
