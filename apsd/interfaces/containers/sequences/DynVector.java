package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer,InsertableAtSequence<Data>,RemovableAtSequence<Data>, Vector<Data>{
  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

    @Override
    default void InsertAt(Data element, Natural idx) {
        if (element == null) throw new NullPointerException("Element cannot be null");
        if (idx == null) throw new NullPointerException("Index cannot be null");
        long index = idx.ToLong();
        long size = Size().ToLong();
        long cap  = Capacity().ToLong();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds for insert: " + index + "; Size: " + size + "!");
        if (size + 1 > cap) Grow();
        ShiftRight(Natural.Of(index));
        SetAt(element, Natural.Of(index));
    }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

    @Override
    default Data AtNRemove(Natural index) {
        if(index.ToLong() < 0 || index.ToLong() >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + index.ToLong() + "; Size: " + Size().ToLong() + "!");
        Data old = GetAt(index);
        ShiftLeft(index, Natural.ONE);
        return old;
    }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

    @Override
    default void ShiftLeft(Natural pos, Natural howMany){
        if(howMany.ToLong() == 0)
            return;
        Vector.super.ShiftLeft(pos, howMany);
        Reduce(howMany);
    }
    @Override
    default void ShiftRight(Natural pos, Natural howMany){
        if(howMany.ToLong() == 0)
            return;
        if(pos.ToLong() < 0 || pos.ToLong() >= Size().ToLong()){
            return;
        }
        if(pos.ToLong() + howMany.ToLong() > Size().ToLong())
            Expand(howMany);
        Vector.super.ShiftRight(pos, howMany);
    }
    @Override
    default DynVector<Data> SubVector(Natural index, Natural size){
        long idx = ExcIfOutOfBound(index);
        return (DynVector<Data>) Vector.super.SubVector(Natural.Of(idx), size);
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    @Override
    Natural Size();
}
