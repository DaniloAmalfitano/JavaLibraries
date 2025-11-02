package apsd.classes.containers.sequences.abstractbases;

 import apsd.classes.utilities.MutableNatural;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.MutableSequence;
 import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<Data> implements Vector<Data>{ // Must implement Vector

  protected Data[] arr;

  // VectorBase
    public VectorBase() {
        ArrayAlloc(new Natural(0));
    }

    public VectorBase(Data[] arr) {
        //if (arr == null) throw new NullPointerException("Array cannot be null!");
        this.arr = arr;
    }
    public VectorBase(Natural size) {
        if(size == null) throw new NullPointerException("Size cannot be null!");
        ArrayAlloc(size);
    }

  // NewVector
    abstract protected VectorBase<Data> NewVector(Data[] arr);

   @SuppressWarnings("unchecked")
   protected void ArrayAlloc(Natural newsize) {
     long size = newsize.ToLong();
     if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
     arr = (Data[]) new Object[(int) size];
   }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    //Clear
    public void Clear() {
        ArrayAlloc(new Natural(0));
    }

    //IsEmpty
    /*public boolean IsEmpty() {
      return arr.length == 0;
    }*/

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...
    //Per ora la capacity è uguale alla size perchè Vector è statico
    public Natural Capacity() {
      return new Natural(arr.length);
    }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
    protected class VectorFIterator implements MutableForwardIterator<Data> {
        protected long index = 0;

        @Override
        public boolean IsValid() {
            return index < arr.length;
        }
        @Override
        public Data DataNNext() {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            return GetAt(Natural.Of(index++));
        }
        @Override
        public void Next() {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            index++;
        }
        public void SetCurrent(Data data) {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            SetAt(data, Natural.Of(index));
        }
        public void Reset() {
            index = 0;
        }
        public Data GetCurrent() {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            return GetAt(Natural.Of(index));
        }
    }
    @Override
    public MutableForwardIterator<Data> FIterator() {
        return new VectorFIterator();
    }

    protected class VectorBIterator implements MutableBackwardIterator<Data> {
        protected long index = arr.length - 1;

        @Override
        public boolean IsValid() {
            return index >= 0;
        }
        @Override
        public void Prev() {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            index--;
        }
        @Override
        public Data DataNPrev() {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            return GetAt(Natural.Of(index--));
        }
        public void SetCurrent(Data data) {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            SetAt(data, Natural.Of(index));
        }
        public void Reset() {
            index = arr.length - 1;
        }
        public Data GetCurrent() {
            if(!IsValid())
                throw new IllegalStateException("Iterator is not valid!");
            return GetAt(Natural.Of(index));
        }
    }
    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return new VectorBIterator();
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
    @Override
    abstract public Data GetAt(Natural index);
    @Override
    abstract public void SetAt(Data data, Natural index);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // ...
    @Override
    @SuppressWarnings("unchecked")
    public MutableSequence<Data> SubSequence(Natural start, Natural end) {
        if(IsInBound(start) && IsInBound(end) && start.ToLong() <= end.ToLong()) {
            int newSize = (int)(end.ToLong() - start.ToLong());
            Data[] newArr = (Data[]) (new Object[(int)newSize]);
            /*for(int i = 0; i < newSize.ToLong(); i++) {
                newArr[i] = GetAt(Natural.Of(start.ToLong() + i));
            }*/
            System.arraycopy(arr, 0, newArr, 0, newSize);
            return NewVector(newArr);
        }
        return null;
    }
}
