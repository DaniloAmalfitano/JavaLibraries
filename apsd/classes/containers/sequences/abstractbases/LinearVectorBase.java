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

    @Override
    public void Realloc(Natural size) {
        if (size == null) throw new NullPointerException("Size cannot be null!");
        Data[] oldArr = arr;
        ArrayAlloc(size);
        int newCap = arr.length;
        int newLength = Math.min((int)size.ToLong(), newCap);
        int copyLen = Math.min(((oldArr != null) ? oldArr.length : 0),newLength);
        if (copyLen > 0) {
            System.arraycopy(oldArr, 0, arr, 0, copyLen);
        }
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

    @Override
    public Data GetAt(Natural index){
        if(index == null) throw new NullPointerException("Index cannot be null!");
        long idx = ExcIfOutOfBound(index);
        return arr[(int)idx];
    }
  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

    @Override
    public void SetAt(Data dat, Natural index){
        if(index == null) throw new NullPointerException("Index cannot be null!");
        long idx = ExcIfOutOfBound(index);
        arr[(int)idx] = dat;
    }

}
