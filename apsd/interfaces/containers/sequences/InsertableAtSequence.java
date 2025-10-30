package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<Data> extends Sequence<Data> { // Must extend Sequence

  // InsertAt
    void InsertAt(Data data, Natural index);

  // InsertFirst
    default void InsertFirst(Data data){
      InsertAt(data, Natural.ZERO);
    }

  // InsertLast
    default void InsertLast(Data data){
      InsertAt(data, Size().Decrement());
    }
}
