package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;


/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<? super Data>> extends Sequence<Data>, SortedIterableContainer<Data> { // Must extend Sequence and SortedIterableContainer

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */
    @Override
    default boolean Exists(Data data){
        return Search(data) != null;
    }
  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data data){
        long n = Size().ToLong();
        if (n == 0) return null;

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
        return null;
  }
}
