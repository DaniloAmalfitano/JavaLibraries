package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<? super Data>> extends OrderedChain<Data>, SortedSequence<Data>{ // Must extend OrderedChain and SortedSequence

    // SearchPredecessor
    default Natural SearchPredecessor(Data element) {
        if (Size().ToLong() == 0) return Natural.ZERO.Decrement();
        Natural lower = Natural.ZERO;
        Natural higher = Size().Decrement();
        while (lower.compareTo(higher) <= 0) {
            Natural medium = Natural.Of(lower.ToLong() + ((higher.ToLong() - lower.ToLong()) / 2));
            if (GetAt(medium).compareTo(element) < 0) {
                lower = medium.Increment();
            } else {
                higher = medium.Decrement();
            }
        }
        return lower.Decrement();
    }


    // SearchSuccessor
    default Natural SearchSuccessor(Data element) {
        if (Size().ToLong() == 0) return Natural.ZERO;
        Natural lower = Natural.ZERO;
        Natural higher = Size().Decrement();
        while (lower.compareTo(higher) <= 0) {
            Natural medium = Natural.Of(lower.ToLong() + ((higher.ToLong() - lower.ToLong()) / 2));
            if (GetAt(medium).compareTo(element) <= 0) {
                lower = medium.Increment();
            } else {
                higher = medium.Decrement();
            }
        }
        return lower;
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  @Override
  default Natural Search(Data data) {
      if (data == null || IsEmpty()) {
          return null;
      }
      Natural lower = Natural.ZERO;
      Natural higher = Size().Decrement();
      while (lower.compareTo(higher) <= 0) {
          Natural medium = Natural.Of(lower.ToLong() + ((higher.ToLong() - lower.ToLong()) / 2));
          int cmp = GetAt(medium).compareTo(data);
          if (cmp == 0) {
              return medium;
          } else if (cmp < 0) {
              lower = medium.Increment();
          } else {
              higher = medium.Decrement();
          }
      }
      return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

   default void Intersection(SortedChain<Data> chn) {
     Natural i = Natural.ZERO, j = Natural.ZERO;
     while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
       int cmp = GetAt(i).compareTo(chn.GetAt(j));
       if (cmp < 0) {
         RemoveAt(i);
       } else {
         j = j.Increment();
         if (cmp == 0) { i = i.Increment(); }
       }
     }
     while (i.compareTo(Size()) < 0) {
       RemoveAt(i);
     }
   }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ...
    @Override
    default Data Min(){
        if(IsEmpty())
            return null;
         return GetAt(Natural.ZERO);
    }
    @Override
    default Data Max(){
        if(IsEmpty())
            return null;
         return GetAt(Size().Decrement());
    }
    @Override
    default void RemoveMin(){
         RemoveAt(Natural.ZERO);
    }
    @Override
    default void RemoveMax(){
         RemoveAt(Size().Decrement());
    }
    @Override
    default Data MinNRemove(){
         Data min = GetAt(Natural.ZERO);
         RemoveAt(Natural.ZERO);
         return min;
    }
    @Override
    default Data MaxNRemove(){
         Data max = GetAt(Size().Decrement());
         RemoveAt(Size().Decrement());
         return max;
    }
    @Override
    default Data Predecessor(Data data) {
        Natural index = SearchPredecessor(data);
        if (index == null) return null;
        if (index.ToLong() >= 0 && index.compareTo(Size()) < 0) return GetAt(index);
        return null;
    }
    @Override
    default Data Successor(Data data){
        Natural index = SearchSuccessor(data);
        if (index.compareTo(Size()) < 0)
            return GetAt(index);
        return null;
    }
    @Override
    default void RemovePredecessor(Data data){
        Natural index = SearchPredecessor(data);
        if (index.compareTo(Natural.ZERO.Decrement()) > 0)
            RemoveAt(index);
    }
    @Override
    default void RemoveSuccessor(Data data) {
        Natural index = SearchSuccessor(data);
        if (index.compareTo(Size()) < 0)
            RemoveAt(index);
    }
    @Override
    default Data PredecessorNRemove(Data data){
        Natural index = SearchPredecessor(data);
        if (index.compareTo(Natural.ZERO.Decrement()) > 0) {
            Data pred = GetAt(index);
            RemoveAt(index);
            return pred;
        }
        return null;
    }
    @Override
    default Data SuccessorNRemove(Data data){
        Natural index = SearchSuccessor(data);
        if (index.compareTo(Size()) < 0) {
            Data succ = GetAt(index);
            RemoveAt(index);
            return succ;
        }
        return null;
    }
}
