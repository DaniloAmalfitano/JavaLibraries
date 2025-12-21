package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

import java.util.Objects;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data>{

  ForwardIterator<Data> FIterator();
  BackwardIterator<Data> BIterator();


    default boolean IsEqual(IterableContainer<Data> other) {
        if (other == null) return false;
        if (this.Size().compareTo(other.Size()) != 0) return false;

        ForwardIterator<Data> it1 = this.FIterator();
        ForwardIterator<Data> it2 = other.FIterator();

        while (it1.IsValid() && it2.IsValid()) {
            Data dataThis = it1.DataNNext();
            Data dataOther = it2.DataNNext();

            if ((dataThis == null && dataOther != null) ||
                    (dataThis != null && !dataThis.equals(dataOther))) {
                return false;
            }
        }
        return !(it1.IsValid() || it2.IsValid());
    }

    /* ************************************************************************ */
    /* Override specific member functions from TraversableContainer             */
    /* ************************************************************************ */

    @Override
    default boolean TraverseForward(Predicate<Data> pred){
        ForwardIterator<Data> it = FIterator();
        while(it.IsValid()){
            if(pred.Apply(it.DataNNext()))
                return true;
        }
        return false;
    }

    @Override
    default boolean TraverseBackward(Predicate<Data> pred){
        BackwardIterator<Data> it = BIterator();
        while(it.IsValid()){
            if(pred.Apply(it.DataNPrev()))
                return true;
        }
        return false;
    }
}
