package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data>{

    @Override
    default Natural Size() {
        return Capacity();
    }

   default void ShiftLeft(Natural pos, Natural num) {
      long idx = ExcIfOutOfBound(pos);
      long size = Size().ToLong();
      long len = num.ToLong();
      len = Math.min(len, size - idx);
      if (len > 0) {
          long wrt = idx;
          for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
            Natural natrdr = Natural.Of(rdr);
            SetAt(GetAt(natrdr), Natural.Of(wrt));
            SetAt(null, natrdr);
          }
          for (; wrt - idx < len; wrt++) {
            SetAt(null, Natural.Of(wrt));
          }
      }
    }
    default void ShiftLeft(Natural pos) {
      ShiftLeft(pos, Natural.ONE);
    }
    default void ShiftRight(Natural pos) {
      ShiftRight(pos, Natural.ONE);
    }

    default void ShiftFirstLeft() {
      ShiftLeft(Natural.Of(0), Natural.ONE);
    }

    default void ShiftLastLeft() {
      ShiftLeft(IsEmpty() ? Natural.Of(0) : Size().Decrement(), Natural.ONE);
    }

  default void ShiftRight(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = Math.min(len, size - idx);

    if (len > 0) {
      long wrt = size - 1;
      for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (long i = idx; i < idx + len; i++) {
        SetAt(null, Natural.Of(i));
      }
    }
  }

  default void ShiftFirstRight() {
    ShiftRight(Natural.Of(0), Natural.ONE);
  }

  default void ShiftLastRight() {
    ShiftRight(IsEmpty() ? Natural.Of(0) : Size().Decrement(), Natural.ONE);
  }

  default Vector<Data> SubVector(Natural start, Natural end) {
    long s = ExcIfOutOfBound(start);
    long e = ExcIfOutOfBound(end);
    if (s > e) throw new IllegalArgumentException("Start index cannot be greater than end index.");
    return (Vector<Data>) SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    @Override
    default boolean IsEmpty() {
      return ReallocableContainer.super.IsEmpty();
    }
}
