package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{ // Must extend RemovableAtSequence

  // InsertIfAbsent
    default boolean InsertIfAbsent(Data data){
        if(!Exists(data)){
            Insert(data);
            return true;
        }
        return false;
    }


  // RemoveOccurrences
    default void RemoveOccurrences(Data data){
        while(Exists(data)){
            Remove(data);
        }
    }


  // SubChain
    default Chain<Data> SubChain(Natural start, Natural end){
        return (Chain<Data>) SubSequence(start, end);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...

}
