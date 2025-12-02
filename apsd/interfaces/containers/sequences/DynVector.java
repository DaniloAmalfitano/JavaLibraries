package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer,InsertableAtSequence<Data>,RemovableAtSequence<Data>, Vector<Data>{ // Must extend ResizableContainer, InsertableAtSequence, RemovableAtSequence, and Vector
    @Override
    Natural Size();
  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // ...
    @Override
    default void InsertAt(Data data, Natural index){
        long idx = index.ToLong();
        if (idx < 0 || idx > Size().ToLong()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size().ToLong() + "!");
        }
        if(idx == Size().ToLong()){
            Expand();
            SetAt(data, index);
        } else {
            //Expand();
            ShiftRight(Natural.Of(idx), Natural.Of(1));
            SetAt(data, index);
        }
    }
    @Override
    default Data AtNRemove(Natural index){
        long idx = ExcIfOutOfBound(index);
        Data data = GetAt(Natural.Of(idx));
        if (idx < Size().ToLong() - 1)
            ShiftLeft(Natural.Of(idx + 1), Natural.Of(1));
        Shrink();
        Reduce();
        return data;
    }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  // ...
    @Override
    default void ShiftLeft(Natural pos, Natural howMany){
        /*if(howMany.ToLong() == 0)
            return;*/
        Vector.super.ShiftLeft(pos, howMany);
    }
    @Override
    default void ShiftRight(Natural pos, Natural howMany){
        if(howMany.ToLong() == 0)
            return;

        long idx = ExcIfOutOfBound(pos);
        if(idx + howMany.ToLong() > Size().ToLong())
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

  // ...

}
