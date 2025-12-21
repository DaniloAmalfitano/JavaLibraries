package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data>{
  protected long size = 0L;

  public DynLinearVectorBase() {
        super();
    }

    public DynLinearVectorBase(Data[] arr) {
        super(arr);
        this.size = arr.length;
    }

    public DynLinearVectorBase(Natural size) {
        super(size);
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
        if(newCapacity == null) return;
        super.Realloc(newCapacity);
        if(size > newCapacity.ToLong()){
            size = newCapacity.ToLong();
        }
    }

    @Override
    public Natural Capacity() {
      return Natural.Of(arr.length);
    }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

      @Override
      public void Expand(Natural increment) {
          if (increment == null) return;
          Grow(increment);
          this.size += increment.ToLong();
      }

    @Override
    public void Reduce(Natural decrement){
        if(decrement == null) return;
        long decVal = decrement.ToLong();
        if(decVal > size) throw new IllegalArgumentException("Decrement size cannot be greater than current size!");

        size -= decVal;

        if (size == 0)
            Realloc(Natural.ZERO);
        else
            Shrink();

    }

    @Override
    public void ArrayAlloc(Natural size) {
        super.ArrayAlloc(size);
    }

}
