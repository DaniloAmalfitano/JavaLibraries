package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data>{ // Must extend CircularVectorBase and implement DynVector

  protected long size = 0L;

  // DynCircularVectorBase
    public DynCircularVectorBase(){
        super();
    }

    public DynCircularVectorBase(long size){
        super();
        this.size = size;
    }
    public DynCircularVectorBase(TraversableContainer<Data> con){
        super(con);
        this.size = con.Size().ToLong();
    }

    public DynCircularVectorBase(Data[] arr) {
        super(arr);
        this.size = arr.length;
    }
    /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
    @Override
    public Natural Size() {
        return Natural.Of(size);
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
    @Override
    public void Clear() {
        super.Clear();
        this.size = 0L;
    }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // ...

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
    public void Reduce(Natural decrement) {
        if(decrement == null) throw new IllegalArgumentException("Decrement argument is null.");
        long actualDecrement = Math.min(decrement.ToLong(), this.size);
        long newSize = this.size - actualDecrement;
        long newCapacity = Math.max(this.Capacity().ToLong() - decrement.ToLong(), newSize);
        newCapacity = Math.max(newCapacity, 0L);
        Realloc(new Natural(newCapacity));
        this.size = newSize;
    }
  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ...
    @Override
    public void ShiftLeft(Natural position, Natural num) {
      super.ShiftLeft(position, num);
      Reduce(num);
    }

    @Override
    public void ShiftRight(Natural position, Natural num) {
        Expand(num);
        super.ShiftRight(position, num);
    }
    @Override
    public void ArrayAlloc(Natural arraySize){
        if(arraySize == null) throw new IllegalArgumentException("ArraySize argument is null.");
        this.Realloc(arraySize);
        this.size = arraySize.ToLong();
    }
}
