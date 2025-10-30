package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data>{ // Must extend TraversableContainer

  ForwardIterator<Data> FIterator();
  BackwardIterator<Data> BIterator();

    /*default boolean IsEqual(IterableContainer<Data> iterableC) { //Lavoraci
        if (iterableC == null) {
            return false;
        }
        ForwardIterator<Data> it1 = this.FIterator();
        ForwardIterator<Data> it2 = iterableC.FIterator();

        boolean mismatch = it1.ForEachForward(dat -> {
            if (!it2.IsValid()) {
                return true;
            }
            Data other = it2.DataNNext();
            return !dat.equals(other);
        });
        return !mismatch && !it2.IsValid();
    }*/
    default boolean IsEqual(IterableContainer<Data> iterableC) {
        if (iterableC == null) return false;
        if (this.Size() != iterableC.Size()) return false;

        ForwardIterator<Data> otherIt = iterableC.FIterator();

        return !this.TraverseForward(dat -> {
            Data other = otherIt.DataNNext();
            return !dat.equals(other);
        });
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
