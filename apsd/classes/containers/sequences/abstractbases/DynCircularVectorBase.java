package apsd.classes.containers.sequences.abstractbases;

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

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
    @Override
    public Natural Size() {
        return new Natural(size);
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
        this.Realloc(new Natural(this.Capacity().ToLong() + increment.ToLong()));
    }

    @Override
    public void Reduce(Natural decrement) {
        if(decrement == null) throw new IllegalArgumentException("Decrement argument is null.");
        Realloc(new Natural((decrement.ToLong() > this.Capacity().ToLong()) ? 0L :
                Math.max((this.Capacity().ToLong() - decrement.ToLong()), this.size))); //Assicura che non riduca mai sotto la size attuale
    }
  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ...
    @Override
    public void Realloc(Natural newCapacity) {
        super.Realloc(newCapacity);
        if(this.size > newCapacity.ToLong()){
            this.size = newCapacity.ToLong();
        }
    }
    public void ShiftLeft(Natural pos, Natural howMany){
        if(pos == null) throw new IllegalArgumentException("Position argument is null.");
        if(howMany == null) throw new IllegalArgumentException("HowMany argument is null.");
        if(pos.ToLong() + howMany.ToLong() > this.size){
            throw new IndexOutOfBoundsException("ShiftLeft range exceeds vector size.");
        }
        for(long i = pos.ToLong(); i < this.size - howMany.ToLong(); i++){
            this.SetAt(this.GetAt(new Natural(i + howMany.ToLong())),new Natural(i));
        }
        this.size -= howMany.ToLong();
    }

    @Override
    public void ShiftRight(Natural pos, Natural howMany){
        if(pos == null) throw new IllegalArgumentException("Position argument is null.");
        if(howMany == null) throw new IllegalArgumentException("HowMany argument is null.");
        if(this.size + howMany.ToLong() > this.Capacity().ToLong()){
            throw new IndexOutOfBoundsException("ShiftRight exceeds vector capacity.");
        }
        for(long i = this.size - 1L; i >= pos.ToLong(); i--){
            this.SetAt(this.GetAt(new Natural(i)),new Natural(i + howMany.ToLong()));
        }
        this.size += howMany.ToLong();
    }
    @Override
    public void ArrayAlloc(Natural arraySize){
        if(arraySize == null) throw new IllegalArgumentException("ArraySize argument is null.");
        this.Realloc(arraySize);
        this.size = arraySize.ToLong();
    }
}
