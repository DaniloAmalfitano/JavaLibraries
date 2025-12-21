package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{

  default boolean InsertIfAbsent(Data dat){
      if(dat == null) return false;
      return !this.Exists(dat) && this.Insert(dat);
  }

    default void RemoveOccurrences(Data dat){
        while(Exists(dat)){
            Remove(dat);
        }
    }

    default Chain<Data> SubChain(Natural start, Natural end){
        return (Chain<Data>) SubSequence(start, end);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

    @Override
  default Natural Search(Data dat) {
      if (dat == null) return null;
      return RemovableAtSequence.super.Search(dat);
  }
    @Override
    default boolean IsEqual(IterableContainer<Data> other) {
        if (other == null) return false;
        if (this == other) return true;
        if (this.Size().compareTo(other.Size()) != 0) return false;
        ForwardIterator<Data> it1 = this.FIterator();
        ForwardIterator<Data> it2 = other.FIterator();
        while (it1.IsValid() && it2.IsValid()) {
            Data dataThis = it1.DataNNext();
            Data dataOther = it2.DataNNext();
            if ((dataThis == null && dataOther != null) ||
                    (dataThis != null && !dataThis.equals(dataOther))) {
                return false;
            }
        }
        return !(it1.IsValid() || it2.IsValid());
    }
}
