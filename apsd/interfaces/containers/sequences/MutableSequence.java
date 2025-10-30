package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

import apsd.interfaces.containers.base.MutableIterableContainer;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> { // Must extend Sequence and MutableIterableContainer

  // SetAt
    default void SetAt(Data data, long index) {
        long idx = ExcIfOutOfBound(Natural.Of(index));
        MutableForwardIterator<Data> iterator = FIterator();
        iterator.Next(Natural.Of(idx));
        iterator.SetCurrent(data);
    }

  // GetNSetAt
    default Data GetNSetAt(Data data, long index){
        long idx = ExcIfOutOfBound(Natural.Of(index));
        MutableForwardIterator<Data> iterator = FIterator();
        iterator.Next(Natural.Of(idx));
        Data oldData = iterator.GetCurrent();
        iterator.SetCurrent(data);
        return oldData;
    }

  // SetFirst
    default void SetFirst(Data data){
      SetAt(data, 0L);
    }

  // GetNSetFirst
    default Data GetNSetFirst(Data data){
      return GetNSetAt(data, 0L);
    }

  // SetLast
    default void SetLast(Data data){
      SetAt(data, IsEmpty() ? 0L : Size().Decrement().ToLong());
    }

  // GetNSetLast
    default Data GetNSetLast(Data data){
      return GetNSetAt(data, IsEmpty() ? 0L : Size().Decrement().ToLong());
    }

  // Swap
    default void Swap(long index1, long index2){
        long idx1 = ExcIfOutOfBound(Natural.Of(index1));
        long idx2 = ExcIfOutOfBound(Natural.Of(index2));
        if(idx1 != idx2){
            Data data1 = GetAt(Natural.Of(idx1));
            Data data2 = GetAt(Natural.Of(idx2));
            SetAt(data2, idx1);
            SetAt(data1, idx2);
        }
    }
    MutableSequence<Data> SubSequence(Natural startIndex, Natural endIndex);

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...


}
