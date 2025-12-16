package apsd.interfaces.containers.iterators;

import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data>{
    Data DataNPrev();

    default void Prev() {
      if(IsValid()) DataNPrev();
    }
    default void Prev(long n) {
        if(n<0) throw new IllegalArgumentException("Negative argument: " + n);
        for (long i = 0;i < n; i++)
            Prev();
    }
    default boolean ForEachBackward(Predicate<Data> fun) {
        if (fun != null) {
            while (IsValid())
                if (fun.Apply(DataNPrev())) return true; }
        return false;
    }
}
