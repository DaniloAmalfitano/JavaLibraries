package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

abstract public class WOrderedSetITest extends WOrderedSetTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(10);
    NewEmptyContainer();
    TestInsert(4L, true);
    TestInsert(0L, true);
    TestInsert(4L, false);
    TestInsert(3L, true);
    TestInsert(1L, true);
    TestInsert(3L, false);
    TestInsert(2L, true);
    TestInsert(0L, false);
    TestSize(5, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("WOrderedSet Basics")
  public class WOrderedSetBasics {

    @Test
    @DisplayName("Check starting from an Empty WOrderedSet")
    public void Empty() {
      AddTest(8);
      NewEmptyContainer();
      TestExists(0L, false);
      TestFilter(dat -> false);
      TestUnion(ThisContainer());
      TestIntersection(ThisContainer());
      TestDifference(ThisContainer());
      TestSize(0, false);
      TestMin(null);
      TestMax(null);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty WOrderedSet")
    public void NonEmpty() {
      AddTest(48);
      NewNonEmptyContainer();
      TestMin(0L);
      TestMax(4L);
      TestRemoveMin();
      TestMinNRemove(1L);
      TestInsert(-1L, true);
      TestInsert(1L, true);
      TestMin(-1L);
      TestMaxNRemove(4L);
      TestSize(4, false);
      TestInsert(6L, true);
      TestSize(5, false);
      TestMax(6L);
      TestInsert(7L, true);
      TestSize(6, false);
      TestExists(6L, true);
      TestExists(8L, false);
      TestExists(0L, false);
      TestExists(-1L, true);
      TestExists(2L, true);
      TestRemove(5L, false);
      TestRemove(2L, true);
      TestExists(5L, false);
      TestExists(2L, false);
      TestRemoveMax();
      TestPrintContent("");
      TestPredecessor(4L, 3L);
      TestPredecessor(5L, 3L);
      TestSuccessor(2L, 3L);
      TestSuccessor(4L, 6L);
      TestPredecessorNRemove(7L, 6L);
      TestSuccessorNRemove(0L, 1L);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 2L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 2L);
      TestClear();
      TestSize(0, false);
      
      TestClear();
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestInsert(6L, true);
      TestInsert(7L, true);
      TestInsert(8L, true);
      TestInsert(9L, true);
      TestInsert(10L, true);
      TestInsert(11L, true);
      TestSuccessorNRemove(3L, 4L);
      TestPredecessorNRemove(8L, 7L);
      TestPredecessorNRemove(6L, 5L);
      TestPredecessorNRemove(12L, 11L);
    }

  }

  @Nested
  @DisplayName("WOrderedSet Edge Cases")
  public class WOrderedSetEdgeCases {

    @Test
    @DisplayName("Single element set")
    public void SingleElement() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(42L, true);
      TestSize(1, false);
      TestMin(42L);
      TestMax(42L);
      TestExists(42L, true);
      TestPredecessor(42L, null);
      TestSuccessor(42L, null);
      TestPredecessor(50L, 42L);
      TestSuccessor(0L, 42L);
      TestRemove(42L, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestMin(null);
    }

    @Test
    @DisplayName("Predecessor and Successor with gaps")
    public void PredSuccWithGaps() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestPredecessor(15L, 10L);
      TestPredecessor(25L, 20L);
      TestPredecessor(35L, 30L);
      TestSuccessor(15L, 20L);
      TestSuccessor(25L, 30L);
      TestSuccessor(35L, 40L);
      TestPredecessor(5L, null);
      TestSuccessor(55L, null);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove min until empty")
    public void RemoveMinUntilEmpty() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(3L, true);
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestMinNRemove(1L);
      TestSize(2, false);
      TestMinNRemove(2L);
      TestSize(1, false);
      TestMinNRemove(3L);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestMin(null);
      TestMax(null);
    }

    @Test
    @DisplayName("Remove max until empty")
    public void RemoveMaxUntilEmpty() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(3L, true);
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestMaxNRemove(3L);
      TestSize(2, false);
      TestMaxNRemove(2L);
      TestSize(1, false);
      TestMaxNRemove(1L);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestMin(null);
      TestMax(null);
    }

    @Test
    @DisplayName("Consecutive numbers")
    public void ConsecutiveNumbers() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestPredecessor(3L, 2L);
      TestSuccessor(3L, 4L);
      TestPredecessor(1L, null);
      TestSuccessor(5L, null);
      TestRemove(3L, true);
      TestPredecessor(4L, 2L);
      TestSuccessor(2L, 4L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Negative numbers in set")
    public void NegativeNumbers() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(-10L, true);
      TestInsert(-5L, true);
      TestInsert(0L, true);
      TestInsert(5L, true);
      TestInsert(10L, true);
      TestMin(-10L);
      TestMax(10L);
      TestPredecessor(0L, -5L);
      TestSuccessor(0L, 5L);
      TestPredecessor(-7L, -10L);
      TestSuccessor(-7L, -5L);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 0L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Duplicate insert rejection")
    public void DuplicateRejection() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(5L, false);
      TestInsert(5L, false);
      TestSize(1, false);
      TestInsert(10L, true);
      TestInsert(10L, false);
      TestSize(2, false);
      TestInsert(5L, false);
      TestInsert(10L, false);
      TestSize(2, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove non-existent element")
    public void RemoveNonExistent() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(5L, true);
      TestRemove(2L, false);
      TestRemove(4L, false);
      TestRemove(0L, false);
      TestSize(3, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Predecessor/Successor with null value")
    public void PredSuccNullValue() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      // Null handling
      assertNull(ThisContainer().Predecessor(null));
      assertNull(ThisContainer().Successor(null));
      assertNull(ThisContainer().PredecessorNRemove(null));
      assertNull(ThisContainer().SuccessorNRemove(null));
    }

    @Test
    @DisplayName("Multiple RemoveMin/RemoveMax operations")
    public void MultipleRemoveMinMax() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(3L, true);
      TestInsert(7L, true);
      TestInsert(1L, true);
      TestInsert(9L, true);
      TestMinNRemove(1L);
      TestMaxNRemove(9L);
      TestSize(3, false);
      TestMin(3L);
      TestMax(7L);
      TestRemoveMin();
      TestRemoveMax();
      TestSize(1, false);
      TestMin(5L);
      TestMax(5L);
      TestRemoveMin();
      TestIsEmpty(true, false);
      assertDoesNotThrow(() -> ThisContainer().RemoveMin());
      assertDoesNotThrow(() -> ThisContainer().RemoveMax());
    }

    @Test
    @DisplayName("Large scale Min/Max operations")
    public void LargeScaleMinMax() {
      AddTest(57);
      NewEmptyContainer();
      for (long i = 100L; i > 75L; i--) {
        TestInsert(i, true);
      }
      TestMin(76L);
      TestMax(100L);
      for (int i = 0; i < 10; i++) {
        TestRemoveMin();
      }
      TestMin(86L);
      for (int i = 0; i < 5; i++) {
        TestRemoveMax();
      }
      TestMax(95L);
      TestSize(10, false);
    }
  }

  @Nested
  @DisplayName("WOrderedSet Null Handling")
  public class WOrderedSetNullHandling {

    @Test
    @DisplayName("Insert null returns false")
    public void InsertNullReturnsFalse() {
      AddTest(1);
      NewEmptyContainer();
      assertFalse(ThisContainer().Insert(null));
    }

    @Test
    @DisplayName("Remove null returns false")
    public void RemoveNullReturnsFalse() {
      AddTest(1);
      NewEmptyContainer();
      assertFalse(ThisContainer().Remove(null));
    }

    @Test
    @DisplayName("Exists null returns false")
    public void ExistsNullReturnsFalse() {
      AddTest(1);
      NewEmptyContainer();
      assertFalse(ThisContainer().Exists(null));
    }
  }

}
