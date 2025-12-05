package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data>{ // Must extend LinearVectorBase and implement DynVector

  protected long size = 0L;

  // DynLinearVectorBase
    public DynLinearVectorBase() {
        super();
    }

    public DynLinearVectorBase(Data[] arr) {
        super(arr);
        this.size = arr.length;
    }

    public DynLinearVectorBase(Natural size) {
        super(size);
        this.size = size.ToLong();
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    @Override
    public Natural Size(){
        return Natural.Of(size);
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    @Override
    public void Clear() {
        super.Clear();
        this.size = 0L;
    }


  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */


    @Override
    public void Realloc(Natural newCapacity){
        if(newCapacity == null) throw new NullPointerException("Size cannot be null!");
        super.Realloc(newCapacity);
        if(size > newCapacity.ToLong()){
            size = newCapacity.ToLong();
        }
    }
    @Override
    public Natural Capacity() {
      return Natural.Of(arr.length);
    }
    @Override
    public void ArrayAlloc(Natural size) {//TODO check
      super.ArrayAlloc(size);
    }
  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...
      @Override
      public void Expand(Natural increment) {
          if (increment == null) throw new IllegalArgumentException("Increment argument is null.");
          long newCapacity = this.Capacity().ToLong() + increment.ToLong();
          super.Realloc(new Natural(newCapacity));
          this.size += increment.ToLong();
      }

    @Override
    public void Reduce(Natural newSize){
        if(newSize == null) throw new NullPointerException("Size cannot be null!");
        if(newSize.ToLong() > arr.length){
            throw new IllegalArgumentException("New size cannot be greater than current capacity!");
        }
        size = newSize.ToLong();
    }
    @Override
    public void ShiftLeft(Natural idx, Natural howMany){
        if(idx == null || howMany == null) throw new NullPointerException("Index or howMany cannot be null!");
        if(idx.ToLong() >= size) throw new IndexOutOfBoundsException("Index out of bounds!");
        int howM = (int) Math.min(howMany.ToLong(), size - idx.ToLong());;
        for(int i = (int)idx.ToLong(); i < size - howM; i++){
            arr[i] = arr[i + howM];
        }
        for(int i = (int)(size - howM); i < size; i++){
            arr[i] = null;
        }
        size -= howM;
    }
    @Override
    public void ShiftRight(Natural idx, Natural howMany){
        if(idx == null || howMany == null) throw new NullPointerException("Index or howMany cannot be null!");
        if(idx.ToLong() > size) throw new IndexOutOfBoundsException("Index out of bounds!");
        if(howMany.ToLong() > arr.length - size){
            Realloc(Natural.Of(size + howMany.ToLong()));
        }
        for(int i = (int)(size - 1); i >= idx.ToLong(); i--){
            arr[(int)(i + howMany.ToLong())] = arr[i];
        }
        for(int i = (int)idx.ToLong(); i < idx.ToLong() + howMany.ToLong(); i++){
            arr[i] = null;
        }
        size += howMany.ToLong();
    }
}
