package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data>{ // Must extend ReallocableContainer and MutableSequence

  // ShiftLeft
    @Override
    default Natural Size() {
      return ReallocableContainer.super.Size();
    }

   default void ShiftLeft(Natural pos, Natural num) {
     long idx = ExcIfOutOfBound(pos);
     long size = Size().ToLong();
     long len = num.ToLong();
     len = (len <= size - idx) ? len : size - idx;
     if (len > 0) {
       long iniwrt = idx;
       long wrt = iniwrt;
       for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
         Natural natrdr = Natural.Of(rdr);
         SetAt(GetAt(natrdr), Natural.Of(wrt));
         SetAt(null, natrdr);
       }
       for (; wrt - iniwrt < len; wrt++) {
         SetAt(null, Natural.Of(wrt));
       }
     }
   }

  // ShiftFirstLeft
    default void ShiftFirstLeft(Natural num) {
      ShiftLeft(Natural.Of(0), num);
    }

  // ShiftLastLeft
    default void ShiftLastLeft(Natural num) {
      ShiftLeft(IsEmpty() ? Natural.Of(0) : Size().Decrement(), num);
    }

  // ShiftRight
    default void ShiftRight(Natural pos, Natural num) {
      long idx = ExcIfOutOfBound(pos);
      long size = Size().ToLong();
      long len = num.ToLong();
      len = (len <= size - idx) ? len : size - idx;
      if (len > 0) {
        long endwrt = size - 1;
        long wrt = endwrt;
        for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
          Natural natrdr = Natural.Of(rdr);
          SetAt(GetAt(natrdr), Natural.Of(wrt));
          SetAt(null, natrdr);
        }
        for (; endwrt - wrt < len; wrt--) {
          SetAt(null, Natural.Of(wrt));
        }
      }
    }

  // ShiftFirstRight
    default void ShiftFirstRight(Natural num) {
      ShiftRight(Natural.Of(0), num);
    }

  // ShiftLastRight
    default void ShiftLastRight(Natural num) {
      ShiftRight(IsEmpty() ? Natural.Of(0) : Size().Decrement(), num);
    }

  // SubVector
    default Vector<Data> SubVector(Natural start, Natural end) {
      long s = ExcIfOutOfBound(start);
      long e = ExcIfOutOfBound(end);
      if (s > e) {
        throw new IllegalArgumentException("Start index cannot be greater than end index.");
      }
      return (Vector<Data>) SubSequence(start, end);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
    @Override
    default boolean IsEmpty() {
      return ReallocableContainer.super.IsEmpty();
    }
}
