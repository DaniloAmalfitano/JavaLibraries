package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.sequences.InsertableAtSequence;
 import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data>{ // Must extend MutableSequence, InsertableAtSequence, and Chain

  // SubList
    default List<Data> SubList(Natural start,Natural end){
        return (List<Data>)SubSequence(start,end);
    }
    default boolean Insert(Data data){
        InsertAt(data,Size());
        return true;
    }

  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer              */
  /* ************************************************************************ */

  // ...

}
