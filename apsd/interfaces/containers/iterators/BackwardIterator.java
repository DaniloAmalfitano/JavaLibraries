package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data>{ // Must extend Iterator

    Data DataNPrev();

    default void Prev() {
      if(IsValid()) {
          DataNPrev();
      }
    }
    default void Prev(Natural n) {
        for (long i = 0;i < n.ToLong(); i++) {
            Prev();
        }
    }
    default boolean ForEachBackward(Predicate<Data> fun) {
        if (fun != null) {
            while (IsValid()) {
                if (fun.Apply(DataNPrev())) {
                    return true; }
            }
        }
        return false;
    }

}
