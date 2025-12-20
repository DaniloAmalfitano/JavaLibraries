package zapsdtest.simpletest.apsd.classes.containers.sequences.generic;

import apsd.classes.utilities.Natural;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

abstract public class XDynVectorITest extends XDynVectorTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(13);
    NewEmptyContainer();
    TestSetFirst(0L, true);
    TestExpandWithNumber(Natural.Of(1));
    TestSetFirst(0L, false);
    TestExpandWithNumber(Natural.Of(4));
    TestSize(5, false);
    TestSwap(Natural.ZERO, Natural.Of(3), false);
    TestSetFirst(4L, false);
    TestSetAt(3L, Natural.Of(1), false);
    TestSetAt(2L, Natural.Of(2), false);
    TestSetAt(1L, Natural.Of(5), true);
    TestSetLast(1L, false);
    TestSize(5, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("Vector Basics")
  public class VectorBasics {

    @Test
    @DisplayName("Check starting from an Empty Vector")
    public void Empty() {
      AddTest(9);
      NewEmptyContainer();
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestSetFirst(0L, true);
      TestSetLast(0L, true);
      TestGetAt(Natural.Of(1), 0L, true);
      TestSetAt(0L, Natural.Of(2), true);
      TestExists(4L, false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 0L);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty Vector")
    public void NonEmpty() {
      AddTest(25);
      NewNonEmptyContainer();
      TestGetFirst(4L, false);
      TestGetLast(1L, false);
      TestSetFirst(5L, false);
      TestSetLast(4L, false);
      TestExists(4L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 14L);
      TestFoldForward((dat, acc) -> acc * dat, 1L, 0L);
      TestGetAt(Natural.Of(3), 0L, false);
      TestSetAt(1L, Natural.Of(3), false);
      TestFoldForward((dat, acc) -> acc * dat, 1L, 120L);
      TestPrintContent("");
      TestRealloc(Natural.Of(2));
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc * dat, 1L, 15L);
      TestRealloc(Natural.Of(4));
      TestShiftRight(Natural.ONE, Natural.Of(2));
      TestPrintContent("");
      TestSize(4L, false);
      TestShiftLeft(Natural.ZERO, Natural.ONE);
      TestSize(3L, false);
      TestShiftRight(Natural.ZERO, Natural.Of(2));
      TestSize(5L, false);
      TestPrintContent("");
      TestClear();
      TestSize(0L, false);
    }

  }

  @Nested
  @DisplayName("DynVector Edge Cases")
  public class DynVectorEdgeCases {

    @Test
    @DisplayName("Expand from zero")
    public void ExpandFromZero() {
      AddTest(8);
      NewEmptyContainer();
      TestSize(0, false);
      TestExpandWithNumber(Natural.Of(5));
      TestSize(5, false);
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(4), false);
      TestGetAt(Natural.ZERO, 10L, false);
      TestGetAt(Natural.Of(4), 20L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Multiple expansions")
    public void MultipleExpansions() {
      AddTest(10);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(2));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestExpandWithNumber(Natural.Of(3));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestSetAt(5L, Natural.Of(4), false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Shrink preserves front elements")
    public void ShrinkPreservesFront() {
      AddTest(8);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestRealloc(Natural.Of(2));
      TestSize(2, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
    }

    @Test
    @DisplayName("ShiftLeft reduces size")
    public void ShiftLeftReducesSize() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestSize(5, false);
      TestShiftLeft(Natural.Of(2), Natural.Of(2));
      TestSize(3, false);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc + dat, 0L, 8L);
    }

    @Test
    @DisplayName("ShiftRight increases size")
    public void ShiftRightIncreasesSize() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSize(3, false);
      TestShiftRight(Natural.ONE, Natural.Of(2));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(3), 2L, false);
      TestGetAt(Natural.Of(4), 3L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Insert at with expansion")
    public void InsertAtWithExpansion() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(3L, Natural.Of(1), false);
      TestSetAt(5L, Natural.Of(2), false);
      TestInsertAtWithAutoExpansion(Natural.ONE, 2L);
      TestSize(4, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 3L, false);
      TestGetAt(Natural.Of(3), 5L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove at with reduction")
    public void RemoveAtWithReduction() {
      AddTest(12);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestAtNRemoveWithAutoReduction(Natural.Of(2), 3L);
      TestSize(4, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 4L, false);
      TestGetAt(Natural.Of(3), 5L, false);
    }

    @Test
    @DisplayName("Expand/Reduce with null throw")
    public void ExpandReduceNullThrow() {
      AddTest(2);
      NewEmptyContainer();
      assertThrows(NullPointerException.class, () -> ThisContainer().Expand(null));
      assertThrows(NullPointerException.class, () -> ThisContainer().Reduce(null));
    }

    @Test
    @DisplayName("Expand(0) and Reduce(0) are no-ops")
    public void ExpandReduceZeroNoOp() {
      AddTest(6);
      NewEmptyContainer();
      TestSize(0, false);

      assertDoesNotThrow(() -> ThisContainer().Expand(Natural.ZERO));
      TestSize(0, false);

      assertDoesNotThrow(() -> ThisContainer().Reduce(Natural.ZERO));
      TestSize(0, false);

      // sanity: after some growth, still no-op
      ThisContainer().Expand(Natural.Of(3));
      TestSize(3, false);
      assertDoesNotThrow(() -> ThisContainer().Expand(Natural.ZERO));
      assertDoesNotThrow(() -> ThisContainer().Reduce(Natural.ZERO));
      TestSize(3, false);
    }

    @Test
    @DisplayName("Reduce more than current size throws")
    public void ReduceTooMuchThrows() {
      AddTest(3);
      NewEmptyContainer();
      ThisContainer().Expand(Natural.Of(3));
      TestSize(3, false);
      assertThrows(IllegalArgumentException.class, () -> ThisContainer().Reduce(Natural.Of(4)));
    }

    @Test
    @DisplayName("Multiple consecutive expansions and reductions")
    public void MultipleExpandReduce() {
      AddTest(13);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(4), false);
      TestExpandWithNumber(Natural.Of(3));
      TestSize(8, false);
      TestSetAt(3L, Natural.Of(7), false);
      TestRealloc(Natural.Of(5));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(4), 2L, false);
      TestExpandWithNumber(Natural.Of(2));
      TestSize(7, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("InsertAt/RemoveAt at boundaries with auto expand/reduce")
    public void InsertRemoveAtBoundaries() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestInsertAtWithAutoExpansion(Natural.ZERO, 0L);
      TestSize(4, false);
      TestGetAt(Natural.ZERO, 0L, false);
      TestGetAt(Natural.Of(1), 1L, false);
      TestAtNRemoveWithAutoReduction(Natural.ZERO, 0L);
      TestSize(3, false);
      TestGetAt(Natural.ZERO, 1L, false);
    }
  }

  @Nested
  @DisplayName("DynVector Null Parameter Tests")
  public class DynVectorNullParameterTests {

    @Test
    @DisplayName("GetAt with null throws")
    public void GetAtNullThrows() {
      AddTest(1);
      NewNonEmptyContainer();
      assertThrows(NullPointerException.class, () -> ThisContainer().GetAt(null));
    }

    @Test
    @DisplayName("SetAt with null position throws")
    public void SetAtNullThrows() {
      AddTest(1);
      NewNonEmptyContainer();
      assertThrows(NullPointerException.class, () -> ThisContainer().SetAt(1L, null));
    }

    @Test
    @DisplayName("Swap with null positions throws")
    public void SwapNullThrows() {
      AddTest(2);
      NewNonEmptyContainer();
      assertThrows(NullPointerException.class, () -> ThisContainer().Swap(null, Natural.ZERO));
      assertThrows(NullPointerException.class, () -> ThisContainer().Swap(Natural.ZERO, null));
    }

    @Test
    @DisplayName("InsertAt with null throws")
    public void InsertAtNullThrows() {
      AddTest(1);
      NewNonEmptyContainer();
      assertThrows(NullPointerException.class, () -> ThisContainer().InsertAt(1L, null));
    }

    @Test
    @DisplayName("RemoveAt with null throws")
    public void RemoveAtNullThrows() {
      AddTest(1);
      NewNonEmptyContainer();
      assertThrows(NullPointerException.class, () -> ThisContainer().RemoveAt(null));
    }
  }

}
