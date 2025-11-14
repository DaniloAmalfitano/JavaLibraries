package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data>{ // Must extend Iterator

    Data DataNNext();

    default void Next() {
      if(IsValid()) {
          DataNNext();
      }
    }
    default void Next(long n) {
      for (long i = 0;i < n; i++) {
          Next();
      }
    }
    default boolean ForEachForward(Predicate<Data> fun) {
        if (fun != null) {
            while (IsValid()) {
                if (fun.Apply(DataNNext())) {
                    return true; }
            }
        }
        return false;
    }
}