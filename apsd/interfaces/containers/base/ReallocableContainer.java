package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

/** Interface: ClearableContainer che è anche Reallocable. */
public interface ReallocableContainer extends ClearableContainer,Reallocable {

  double GROW_FACTOR = 2.0;
  double SHRINK_FACTOR = 2.0;

  Natural Capacity();

  default void Grow() {
    Grow(Natural.ONE);
  }
  default void Grow(Natural number) {
    Realloc(Natural.Of((long)(Capacity().ToLong() + number.ToLong() * GROW_FACTOR)));
  }

  default void Shrink() { Realloc(Natural.Of((long) (Capacity().ToLong() / SHRINK_FACTOR))); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  default Natural Size() {
    return Capacity();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  default void Clear(){
    Realloc(Natural.ZERO);
  }
}
