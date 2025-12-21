package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer,InsertableAtSequence<Data>,RemovableAtSequence<Data>, Vector<Data>{
  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

    @Override
    default void InsertAt(Data elem, Natural pos) {
        if (pos == null) throw new NullPointerException("Position cannot be null");
        if (pos.compareTo(Size()) > 0) throw new IndexOutOfBoundsException("out of bound");

        long idx = pos.ToLong();
        long curSize = Size().ToLong();

        if (idx == curSize)
            Expand();
        else
            ShiftRight(pos);
        SetAt(elem, pos);
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
        if(howMany.ToLong() == 0) return;
        if(pos.ToLong() < 0 || pos.ToLong() >= Size().ToLong()) return;
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
