package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data>{ // Must extend IterableContainer

  // GetAt
  default Data GetAt(Natural idx) {
      long index = ExcIfOutOfBound(idx);
      ForwardIterator<Data> iterator = this.FIterator();
      iterator.Next(Natural.Of(index));
      return iterator.GetCurrent();
  }

  // GetFirst
    default Data GetFirst(){
      return GetAt(Natural.ZERO);
    }
  // GetLast
    default Data GetLast(){
        return GetAt(IsEmpty() ? Natural.ZERO : Size().Decrement());
    }

  default Natural Search(Data data) {
      final Box<Long> index = new Box<>(-1L);
      TraverseForward(elem -> {
          index.Set(index.Get() + 1);
          return ((data == null && elem == null) || (data != null && data.equals(elem)));
      });
      return Natural.Of(index.Get());
  }


  // IsInBound
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

  // SubSequence
    Sequence<Data> SubSequence(Natural start, Natural end);
}
