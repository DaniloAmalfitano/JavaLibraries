package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

import apsd.interfaces.containers.base.MutableIterableContainer;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> { // Must extend Sequence and MutableIterableContainer

  // SetAt
    default void SetAt(Data data, Natural index) {
        long idx = ExcIfOutOfBound(index);
        MutableForwardIterator<Data> iterator = FIterator();
        iterator.Next(idx);
        iterator.SetCurrent(data);
    }

  // GetNSetAt
    default Data GetNSetAt(Data data, Natural index){
        long idx = ExcIfOutOfBound(index);
        MutableForwardIterator<Data> iterator = FIterator();
        iterator.Next(idx);
        Data oldData = iterator.GetCurrent();
        iterator.SetCurrent(data);
        return oldData;
    }

  // SetFirst
    default void SetFirst(Data data){
      SetAt(data, Natural.ZERO);
    }

  // GetNSetFirst
    default Data GetNSetFirst(Data data){
      return GetNSetAt(data, Natural.ZERO);
    }

  // SetLast
    default void SetLast(Data data){
      SetAt(data, IsEmpty() ? Natural.ZERO : Size().Decrement());
    }

  // GetNSetLast
    default Data GetNSetLast(Data data){
      return GetNSetAt(data, IsEmpty() ? Natural.ZERO : Size().Decrement());
    }

  // Swap
    default void Swap(Natural index1, Natural index2){
        long idx1 = ExcIfOutOfBound(Natural.Of(index1));
        long idx2 = ExcIfOutOfBound(Natural.Of(index2));
        if(idx1 != idx2){
            Data data1 = GetAt(Natural.Of(idx1));
            Data data2 = GetAt(Natural.Of(idx2));
            SetAt(data2, Natural.Of(idx1));
            SetAt(data1, Natural.Of(idx2));
        }
    }
    MutableSequence<Data> SubSequence(Natural startIndex, Natural endIndex);

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...


}
