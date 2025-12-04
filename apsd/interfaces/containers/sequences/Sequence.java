package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data>{

    default Data GetAt(Natural idx) {
        long index = ExcIfOutOfBound(idx);
        ForwardIterator<Data> iterator = this.FIterator();
        iterator.Next(index);
        return iterator.GetCurrent();
    }

    default Data GetFirst(){
        if(IsEmpty()) throw new IndexOutOfBoundsException("Sequence is empty!");
        return GetAt(Natural.ZERO);
    }

    default Data GetLast(){
        if(IsEmpty()) throw new IndexOutOfBoundsException("Sequence is empty!");
        return GetAt(Size().Decrement());
    }

    default Natural Search(Data data) {
        long index = 0;
        ForwardIterator<Data> itr = FIterator();
        while (itr.IsValid()) {
            Data elem = itr.GetCurrent();
            boolean match = (data == null && elem == null) || (data != null && data.equals(elem));
            if (match) {
                return Natural.Of(index);
            }

            itr.Next();
            index++;
        }
        return null;
    }

    default boolean IsInBound(Natural num) {
        if (num == null) throw new NullPointerException("Natural number cannot be null!");
        long idx = num.ToLong();
        return idx < Size().ToLong();
    }

    default long ExcIfOutOfBound(Natural num) {
        if (num == null) throw new NullPointerException("Natural number cannot be null!");
        long idx = num.ToLong();
        if (idx >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
        return idx;
    }

    Sequence<Data> SubSequence(Natural start, Natural end);
}
