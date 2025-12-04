package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.sequences.InsertableAtSequence;
 import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data>{ // Must extend MutableSequence, InsertableAtSequence, and Chain

  // SubList
  default List<Data> SubList(Natural start, Natural end) {
      long startIndex = ExcIfOutOfBound(start);
      long endIndex = ExcIfOutOfBound(end);
      if (startIndex > endIndex) throw new IllegalArgumentException("Start index cannot be greater than end index.");
      return (List<Data>) SubSequence(start, end);
  }

    @Override
    default boolean Insert(Data data) {
        InsertFirst(data);
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer              */
  /* ************************************************************************ */

  // ...

}
