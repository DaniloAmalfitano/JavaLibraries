package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

import apsd.interfaces.containers.base.MutableIterableContainer;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> {

    default void SetAt(Data data, Natural index) {
        long idx = ExcIfOutOfBound(index);
        MutableForwardIterator<Data> iterator = FIterator();
        iterator.Next(idx);
        iterator.SetCurrent(data);
    }

    default Data GetNSetAt(Data data, Natural index){
        long idx = ExcIfOutOfBound(index);
        MutableForwardIterator<Data> iterator = FIterator();
        iterator.Next(idx);
        Data oldData = iterator.GetCurrent();
        iterator.SetCurrent(data);
        return oldData;
    }

    default void SetFirst(Data data){
        SetAt(data, Natural.ZERO);
    }

    default Data GetNSetFirst(Data data){
        return GetNSetAt(data, Natural.ZERO);
    }

    default void SetLast(Data data){
        SetAt(data, IsEmpty() ? Natural.ZERO : Size().Decrement());
    }

    default Data GetNSetLast(Data data){
        return GetNSetAt(data, IsEmpty() ? Natural.ZERO : Size().Decrement());
    }

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

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

    @Override
    MutableSequence<Data> SubSequence(Natural startIndex, Natural endIndex);
}
