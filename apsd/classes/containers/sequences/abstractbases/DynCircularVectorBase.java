package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data>{

  protected long size = 0L;

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

    @Override
    public Natural Size() {
        return Natural.Of(size);
    }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */




  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

    @Override
    public void Realloc(Natural newCapacity) {
        if(newCapacity == null) throw new NullPointerException("Size cannot be null!");
        super.Realloc(newCapacity);
        if(size > newCapacity.ToLong()) {
            size = newCapacity.ToLong();
        }
    }

    @Override
    public void Clear() {
        super.Clear();
        this.size = 0L;
    }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

    @Override
    public void Expand(Natural increment) {
        if (increment == null) throw new IllegalArgumentException("Increment argument is null.");
        long newCapacity = this.Capacity().ToLong() + increment.ToLong();
        super.Realloc(Natural.Of(newCapacity));
        this.size += increment.ToLong();
    }

    @Override
    public void Reduce(Natural decrement) {
        if (decrement == null) throw new IllegalArgumentException("Decrement argument is null.");
        if (decrement.ToLong() > this.size) throw new IllegalArgumentException("Decrement greater than current size.");
        this.size -= decrement.ToLong();
    }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

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
        super.ArrayAlloc(arraySize);
        start = 0L;
    }

}
