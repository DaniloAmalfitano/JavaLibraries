package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer extends ReallocableContainer{ // Must extend ReallocableContainer

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

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
      if(Capacity().ToLong() > Integer.MAX_VALUE)
        throw new OutOfMemoryError("Capacity exceeds maximum integer value.");
      if ((long) (THRESHOLD_FACTOR * (Size().ToLong() + dimension.ToLong())) >= Capacity().ToLong())
        ReallocableContainer.super.Grow(dimension);
    }
    @Override
    default void Shrink() {
      if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong())
        ReallocableContainer.super.Shrink();
    }
}
