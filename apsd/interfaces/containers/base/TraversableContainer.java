package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

/** Interface: MembershipContainer con supporto all'attraversamento. */
public interface TraversableContainer<Data> extends MembershipContainer<Data> { // Must extend MembershipContainer

  // TraverseForward
    boolean TraverseForward(Predicate<Data> pred);
  // TraverseBackward
    boolean TraverseBackward(Predicate<Data> pred);

  default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
      final Box<Acc> acc = new Box<>(ini);
      if (fun != null) TraverseForward(dat -> { acc.Set(fun.Apply(dat, acc.Get())); return false; });
      return acc.Get();
  }

  // FoldBackward
    default <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
        final Box<Acc> acc = new Box<>(ini);
        if (fun != null) TraverseBackward(dat -> { acc.Set(fun.Apply(dat, acc.Get())); return false; });
        return acc.Get();
    }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

    @Override
    default Natural Size(){
        final MutableNatural count = new MutableNatural();
        TraverseForward(data -> {count.Increment(); return false;});
        return count.ToNatural();
    }

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

    @Override
    default boolean Exists(Data element){
        return this.TraverseForward(elem -> (element == null && elem == null) || (element != null && element.equals(elem)));
    }
}
