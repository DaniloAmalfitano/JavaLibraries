package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> {

    protected long start = 0L;

    public CircularVectorBase() {
        super();
    }

    public CircularVectorBase(Data[] arr) {
        super.arr = arr;
    }
    public CircularVectorBase(TraversableContainer<Data> con) {
        super(con);
    }

    @Override
    public void ArrayAlloc(Natural size) {
        if( size == null) {
            throw new NullPointerException("Size cannot be null!");
        }
        super.ArrayAlloc(size);
        start = 0L;
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
        long oldCapacity = oldArr.length;
        super.ArrayAlloc(size);
        long newCapacity = arr.length;
        start = 0L;
        if (newCapacity == 0) return;
        long copyCount = Math.min(oldLogicalSize, newCapacity);
        for (int i = 0; i < copyCount; i++) {
            long oldIdx = ((oldStart + i) % oldCapacity);
            arr[i] = oldArr[(int)oldIdx];
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    @Override
    public Data GetAt(Natural index) {
        if (index == null) throw new NullPointerException("Index cannot be null!");
        if (index.ToLong() < 0 || index.ToLong() >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + index.ToLong());
        return arr[(int) ((start + index.ToLong()) % arr.length)];
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    @Override
    public void SetAt(Data dat, Natural index) {
        if (index == null) throw new NullPointerException("Index cannot be null!");
        if (index.ToLong() < 0 || index.ToLong() >= Size().ToLong()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index.ToLong());
        }
        arr[(int) ((start + index.ToLong()) % arr.length)] = dat;
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                      */
    /* ************************************************************************ */

    @Override
    public void ShiftLeft(Natural position, Natural howMany) {
        long index = ExcIfOutOfBound(position);
        long size = Size().ToLong();
        long posToShift = howMany.compareTo(Natural.Of(size-index)) < 0 ? howMany.ToLong() : size - index;
        if (index < size - (index + posToShift)) {
            long iniwrt = index - 1 + posToShift;
            long wrt = iniwrt;
            for (long rdr = index - 1; rdr >= 0; rdr--, wrt--) {
                Natural natrdr = Natural.Of(rdr);
                SetAt(GetAt(natrdr), Natural.Of(wrt));
                SetAt(null, natrdr);
            }
            for (; iniwrt - wrt < posToShift; wrt--) {
                SetAt(null, Natural.Of(wrt));
            }
            start = (start + posToShift) % arr.length;
        } else {
            long wrt = index;
            long rdr = index + posToShift;
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
    public void ShiftRight(Natural position, Natural howMany) {
        long index = ExcIfOutOfBound(position);
        long size = Size().ToLong();
        long posToShift = howMany.ToLong();
        if (arr == null || arr.length == 0) return;
        for (long i = size - 1; i >= index; i--) {
            long wrt = i + posToShift;
            Natural natRdr = Natural.Of(i);
            long physicalWrtIndex = (start + wrt) % arr.length;
            arr[(int) physicalWrtIndex] = GetAt(natRdr);
        }
        for (long i = index; i < index + posToShift; i++) {
            long physicalNullIndex = (start + i) % arr.length;
            arr[(int) physicalNullIndex] = null;
        }
    }
}