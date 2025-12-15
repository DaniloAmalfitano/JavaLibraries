package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer extends ReallocableContainer{

  double THRESHOLD_FACTOR = 2.0;

  default void Expand() {
    Expand(Natural.ONE);
  }
  void Expand(Natural nAt);

  default void Reduce() {
    Reduce(Natural.ONE);
  }
  void Reduce(Natural nAt);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  default void Grow(Natural dimension) {
    if(Size().ToLong() + dimension.ToLong() * 2 > Integer.MAX_VALUE)
      throw new OutOfMemoryError("Requested size exceeds maximum array size!");
    if ((long) (THRESHOLD_FACTOR * (Size().ToLong() + dimension.ToLong())) >= Capacity().ToLong())
      ReallocableContainer.super.Grow(dimension);
  }
  @Override
  default void Shrink() {
    if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong())
      ReallocableContainer.super.Shrink();
  }
}
