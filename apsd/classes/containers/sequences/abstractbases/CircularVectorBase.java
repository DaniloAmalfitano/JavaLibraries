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
    @Override
    public void Realloc(Natural size) {
        if (size == null) {
            throw new NullPointerException("Size cannot be null!");
        }
        if(arr == null) {
            super.ArrayAlloc(size);
            start = 0L;
            return;
        }
        Data[] oldArr = arr;
        long oldStart = start;
        long oldLogicalSize = Size().ToLong();
        int oldCapacity = oldArr.length;
        super.ArrayAlloc(size);
        int newCapacity = (arr == null) ? 0 : arr.length;
        start = 0L;
        if (oldLogicalSize == 0) {
            return;
        }
        if (newCapacity == 0) {
            return;
        }
        int copyCount = (int) Math.min(oldLogicalSize, newCapacity);
        for (int i = 0; i < copyCount; i++) {
            int oldIdx = (int) ((oldStart + i) % oldCapacity);
            arr[i] = oldArr[oldIdx];
        }

    }



    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    public void ShiftLeft(Natural position, Natural num) {
        long index = ExcIfOutOfBound(position);
        long size = Size().ToLong();
        long len = num.ToLong();
        len = Math.min(len, size - index);
        if (index < size - (index + len)) {
            long iniwrt = index - 1 + len;
            long wrt = iniwrt;
            for (long rdr = index - 1; rdr >= 0; rdr--, wrt--) {
                Natural natrdr = Natural.Of(rdr);
                SetAt(GetAt(natrdr), Natural.Of(wrt));
                SetAt(null, natrdr);
            }
            for (; iniwrt - wrt < len; wrt--) {
                SetAt(null, Natural.Of(wrt));
            }
            start = (start + len) % arr.length;
        } else {
            long wrt = index;
            long rdr = index + len;
            while (rdr < size) {
                SetAt(GetAt(Natural.Of(rdr)), Natural.Of(wrt));
                rdr++;
                wrt++;
            }
            while (wrt < size) {
                SetAt(null, Natural.Of(wrt));
                wrt++;
            }
        }
    }
    @Override
    public void ShiftRight(Natural position, Natural num) {
        long index = ExcIfOutOfBound(position);
        long size = Size().ToLong();
        long len = num.ToLong();
        if (arr == null || arr.length == 0) return;
        for (long i = size - 1; i >= index; i--) {
            long wrt = i + len;
            Natural natRdr = Natural.Of(i);
            Data dataToMove = GetAt(natRdr);
            long physicalWrtIndex = (start + wrt) % arr.length;
            arr[(int) physicalWrtIndex] = dataToMove;
        }
        for (long i = index; i < index + len; i++) {
            long physicalNullIndex = (start + i) % arr.length;
            arr[(int) physicalNullIndex] = null;
        }
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
        if (index.ToLong() < 0 || index.ToLong() >= Size().ToLong()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index.ToLong());
        }
        return arr[(int) ((start + index.ToLong()) % arr.length)];
    }

    @Override
    public void SetAt(Data value, Natural index) {
        if (index == null) throw new NullPointerException("Index cannot be null!");
        long idx = ExcIfOutOfBound(index);
        arr[(int) ((start + idx) % arr.length)] = value;
    }

    @Override
    public void ArrayAlloc(Natural size) {
        if( size == null) {
            throw new NullPointerException("Size cannot be null!");
        }
        super.ArrayAlloc(size);
        start = 0L;
    }
}