package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<Data> extends VectorBase<Data>{ // Must extend VectorBase

  // LinearVectorBase
    public LinearVectorBase() {
        super();
    }

    public LinearVectorBase(Data[] arr) {
        super(arr);
    }

    public LinearVectorBase(Natural size) {
        super(size);
    }
    public LinearVectorBase(TraversableContainer<Data> con) {
        super(con);
    }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // ...
    @Override
    public void Realloc(Natural size){
        if(size == null) throw new NullPointerException("Size cannot be null!");
        Data[] oldArr = arr;
        ArrayAlloc(size);
        int minSize = size.compareTo(Natural.Of(arr.length)) < 0 ? (int)size.ToLong() : arr.length;
        System.arraycopy(oldArr, 0, arr, 0, minSize);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
    @Override
    public void SetAt(Data element, Natural index){
        if(index == null) throw new NullPointerException("Index cannot be null!");
        if(index.ToLong() >= arr.length) throw new IndexOutOfBoundsException("Index out of bounds!");
        arr[(int)index.ToLong()] = element;
    }
    @Override
    public Data GetAt(Natural index){
        if(index == null) throw new NullPointerException("Index cannot be null!");
        if(index.ToLong() >= arr.length) throw new IndexOutOfBoundsException("Index out of bounds!");
        return arr[(int)index.ToLong()];
    }
  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // ...

}
