package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

    protected long start = 0L;

    // CircularVectorBase
    public CircularVectorBase() {
        super();
    }

    public CircularVectorBase(Data[] arr) {
        super.arr = arr;
    }
    public CircularVectorBase(TraversableContainer<Data> con) {
        super(con);
    }
    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    /*@Override
    public void Realloc(Natural size) {
        if (size == null) throw new NullPointerException("Size cannot be null!");
        Data[] oldarr = arr;
        long requested = size.ToLong();
        super.ArrayAlloc(size);

        if (oldarr == null || oldarr.length == 0) {
            start = 0L;
            return;
        }
        int srcLen = oldarr.length;
        int destLen = arr.length;
        int copyLen = (int) Math.min(Math.min(requested, srcLen), destLen);

        for (int i = 0; i < copyLen; i++) {
            arr[i] = oldarr[(int) ((start + i) % srcLen)];
        }

        start = 0L;
    }*/
    /*@Override
    public void Realloc(Natural size) {
        if (size == null) throw new NullPointerException("Size cannot be null!");
        Data[] oldArr = arr;
        long oldStart = start;
        long currentSize = Size().ToLong();

        super.ArrayAlloc(size);

        if (oldArr == null) return;
        int limit = (int) Math.min(currentSize, size.ToLong()); // copy only existing elements

        for (int i = 0; i < limit; i++) {
            arr[i] = oldArr[(int) ((oldStart + i) % oldArr.length)];
        }
        start = 0L;
    }*/
    @Override
    public void Realloc(Natural size) {
        if (size == null) throw new NullPointerException("Size cannot be null!");
        Data[] oldArr = arr;
        long oldStart = start;

        super.ArrayAlloc(size);

        if (oldArr == null || oldArr.length == 0) {
            start = 0L;
            return;
        }
        int limit = (int) Math.min(oldArr.length, size.ToLong());

        for (int i = 0; i < limit; i++) {
            arr[i] = oldArr[(int) ((oldStart + i) % oldArr.length)];
        }
        start = 0L;
    }



    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    /*@Override
    public void ShiftLeft(Natural index, Natural howMany) {
        if (index == null) throw new NullPointerException("Index cannot be null!");
        if (howMany == null) throw new NullPointerException("HowMany cannot be null!");

        long idx = ExcIfOutOfBound(index);
        long size = Size().ToLong();
        long len = howMany.ToLong();
        if (len <= 0 || idx >= size) return;

        if (len > size - idx) len = size - idx;

        for (long i = idx; i + len < size; i++) {
            SetAt(GetAt(Natural.Of(i + len)), Natural.Of(i));
        }
        for (long i = size - len; i < size; i++) {
            SetAt(null, Natural.Of(i));
        }
    }*/

    @Override
    public void ShiftLeft(Natural index, Natural howMany) {
        long idx  = ExcIfOutOfBound(index);
        long size = Size().ToLong();
        long len = howMany.ToLong();
        len = (len <= size) ? len : size - idx;
        if(idx < size - (idx + len)){
            long iniwtr = idx - 1 + len;
            long wrt = iniwtr;
            for(long rdr = wrt - len; rdr <=0; rdr--,wrt--){
                Natural natrdr = Natural.Of(wrt);
                SetAt(GetAt(natrdr), Natural.Of(wrt));
                SetAt(null, natrdr);
            }
            for(; iniwtr - wrt < len; wrt--){
                SetAt(null, Natural.Of(wrt));
            }
            start = (start+len)%size;
        }
        else
            super.ShiftLeft(index, howMany);
    }
    @Override
    public void ShiftRight(Natural index, Natural howMany) {
        long idx  = ExcIfOutOfBound(index);
        long size = Size().ToLong();
        long len = howMany.ToLong();
        len = (len <= size) ? len : size - idx;
        if(idx < size - (idx + len)){
            long iniwtr = size - 1 - len - (size - (idx + len));
            long wrt = iniwtr;
            for(long rdr = wrt + len; rdr < size; rdr++,wrt++){
                Natural natrdr = Natural.Of(wrt);
                SetAt(GetAt(natrdr), Natural.Of(wrt));
                SetAt(null, natrdr);
            }
            for(; wrt - iniwtr < len; wrt++){
                SetAt(null, Natural.Of(wrt));
            }
            start = (start - len + size)%size;
        }
        else
            super.ShiftRight(index, howMany);
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // ...


    /* ************************************************************************ */
    /* Specific member functions of Vector                                      */
    /* ************************************************************************ */

    // ...
    @Override
    public Data GetAt(Natural index) {
        if (index == null) throw new NullPointerException("Index cannot be null!");
        if (IsInBound(index))
            return arr[(int) ((start + index.ToLong()) % arr.length)];
        return null;
    }
    @Override
    public void SetAt(Data value, Natural index) {
        if (index == null) throw new NullPointerException("Index cannot be null!");
        if (IsInBound(index))
            arr[(int) ((start + index.ToLong()) % arr.length)] = value;
    }
    @Override
    public void ArrayAlloc(Natural size) {
        super.ArrayAlloc(size);
        start = 0L;
    }
}