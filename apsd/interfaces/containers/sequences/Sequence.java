package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data>{ // Must extend IterableContainer

  // GetAt
  default Data GetAt(Natural idx) {
      if(IsInBound(idx)) {
          final Box<Data> result = new Box<>(null);
          final Box<Long> counter = new Box<>(0L);

          ForwardIterator<Data> iterator = this.FIterator();
          iterator.ForEachForward(data -> {
              if (counter.Get() == idx.ToLong()) {
                  result.Set(data);
                  return true;
              }
              counter.Set(counter.Get() + 1);
              return false;
          });

          return result.Get();
      }
      return null;
  }

  /*default Data GetAt(Natural idx) {
      if(IsInBound(idx)) {
          ForwardIterator<Data> iterator = this.FIterator();
          long counter = 0;

          while (!iterator.IsValid()) {
              if (counter == idx.ToLong()) {
                  return iterator.GetCurrent();
              }
              iterator.Next();
              counter++;
          }
      }
      return null;
  }*/

  // GetFirst
    default Data GetFirst(){
      if(Size().ToLong() == 0)
          throw new IndexOutOfBoundsException("Sequence is empty!");
      return GetAt(Natural.ZERO);
    }
  // GetLast
    default Data GetLast(){
        if(Size().ToLong() == 0)
            throw new IndexOutOfBoundsException("Sequence is empty!");
        return GetAt(Natural.Of(Size().ToLong() - 1));
    }

  default Natural Search(Data data) {
        final Box<Natural> index = new Box<>(Natural.ZERO);
        final Box<Natural> result = new Box<>(null);

        ForwardIterator<Data> iterator = this.FIterator();
        iterator.ForEachForward(dat -> {
            if ((data == null && dat == null) || (data != null && data.equals(dat))) {
                result.Set(index.Get());
                return true;
            }
            index.Set(Natural.Of(index.Get().ToLong() + 1));
            return false;
        });
        return result.Get();
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
