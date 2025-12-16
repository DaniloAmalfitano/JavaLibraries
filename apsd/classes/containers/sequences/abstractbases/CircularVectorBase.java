package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> {

    protected long start = 0L;

    public CircularVectorBase() {
        super();
    }
    public CircularVectorBase(Natural size){
        super(size);
    }
    public CircularVectorBase(Data[] arr) {
        super.arr = arr;
    }
    public CircularVectorBase(TraversableContainer<Data> con) {
        super(con);
    }

    @Override
    public void ArrayAlloc(Natural size) {
        if( size == null) throw new NullPointerException("Size cannot be null!");
        super.ArrayAlloc(size);
        start = 0L;
    }

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    @Override
    public void Realloc(Natural size) {
        if (size == null) throw new NullPointerException("Size cannot be null!");

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
        long idx = ExcIfOutOfBound(index);
        return arr[(int) ((start + idx) % arr.length)];
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    @Override
    public void SetAt(Data dat, Natural index) {
        if (index == null) throw new NullPointerException("Index cannot be null!");
        long idx = ExcIfOutOfBound(index);
        arr[(int) ((start + idx) % arr.length)] = dat;
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                      */
    /* ************************************************************************ */

    @Override
    public void ShiftLeft(Natural position, Natural howMany) {
        long logicalStart = ExcIfOutOfBound(position);
        long size = Size().ToLong();
        long shiftAmount = Math.min(howMany.ToLong(), size - logicalStart);

        if (shiftAmount <= 0) return;

        if (logicalStart == 0) {
            for (long i = 0; i < shiftAmount; i++)
                SetAt(null, Natural.Of(i));
            start = (start + shiftAmount) % arr.length;
            return;
        }
        long newSize = size - shiftAmount;
        long oldIndex = logicalStart + shiftAmount;
        for (; oldIndex < size; oldIndex++) {
            long newIndex = oldIndex - shiftAmount;
            SetAt(GetAt(Natural.Of(oldIndex)), Natural.Of(newIndex));
        }
        for (long i = newSize; i < size; i++)
            SetAt(null, Natural.Of(i));
    }

    @Override
    public void ShiftRight(Natural pos, Natural num) {
        if (pos == null || num == null) return;

        long idx = ExcIfOutOfBound(pos);
        long size = Size().ToLong();
        long shiftAmount = Math.min(num.ToLong(), size - idx);
        if (shiftAmount <= 0) return;
        for (long i = size - 1; i >= idx + shiftAmount; i--) {
            SetAt(GetAt(Natural.Of(i - shiftAmount)), Natural.Of(i));
        }
        for (long i = idx; i < idx + shiftAmount; i++) {
            SetAt(null, Natural.Of(i));
        }
    }
}