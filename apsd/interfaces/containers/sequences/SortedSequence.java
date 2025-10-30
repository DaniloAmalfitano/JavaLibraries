package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;


/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<? super Data>> extends Sequence<Data>, SortedIterableContainer<Data> { // Must extend Sequence and SortedIterableContainer

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */
    /*@Override
    default boolean Exists(Data data){
        if (data == null) return false;
        long n = Size().ToLong();
        if (n == 0) return false;

        long lower = 0;
        long higher = n - 1;

        while (lower <= higher) {
            long mid = (lower + higher)/2;
            Data midVal = GetAt(Natural.Of(mid));
            int compare = data.compareTo(midVal);
            if (compare == 0) return true;
            if (compare < 0) {
                higher = mid - 1;
            } else {
                lower = mid + 1;
            }
        }
        return false;
    }*/
    @Override
    boolean Exists(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  /*@Override
  default Natural Search(Data data){
        if (data == null) return Natural.Of(-1L);
        long n = Size().ToLong();
        if (n == 0) return Natural.Of(-1L);

        long lower = 0;
        long higher = n - 1;

        while (lower <= higher) {
            long mid = (lower + higher)/2;
            Data midVal = GetAt(Natural.Of(mid));
            int compare = data.compareTo(midVal);
            if (compare == 0) return Natural.Of(mid);
            if (compare < 0) {
                higher = mid - 1;
            } else {
                lower = mid + 1;
            }
        }
        return Natural.Of(-1L);
  }*/
    @Override
    Natural Search(Data data);
}
