package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

/** Interface: ClearableContainer che è anche Reallocable. */
public interface ReallocableContainer extends ClearableContainer,Reallocable { // Must extend ClearableContainer, Reallocable

  double GROW_FACTOR = 2.0; // Must be strictly greater than 1.
  double SHRINK_FACTOR = 2.0; // Must be strictly greater than 1.

  Natural Capacity();

  default void Grow() {
    Realloc(Natural.Of((long)(GROW_FACTOR * this.Capacity().ToLong())));
  }
  default void Grow(Natural number) {
    Realloc(Natural.Of(this.Capacity().ToLong() + number.ToLong()));
  }

  default void Shrink(){
    Realloc(Natural.Of((long)(this.Capacity().ToLong() / SHRINK_FACTOR)));
  }
  default void Shrink(Natural number){
    Realloc(Natural.Of(this.Capacity().ToLong() - number.ToLong()));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override //Non so se devo overridare questa.
  void Clear();
}
