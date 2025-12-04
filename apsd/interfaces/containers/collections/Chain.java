package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{

  default boolean InsertIfAbsent(Data dat){ return !this.Exists(dat) && this.Insert(dat);}

    default void RemoveOccurrences(Data data){
        while(Exists(data)){
            Remove(data);
        }
    }

    default Chain<Data> SubChain(Natural start, Natural end){
        return (Chain<Data>) SubSequence(start, end);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

    @Override
  default Natural Search(Data data) {
      if (data == null) return null;
      return RemovableAtSequence.super.Search(data);
  }
}
