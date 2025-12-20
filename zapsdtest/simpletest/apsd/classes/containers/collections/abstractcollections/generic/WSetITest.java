package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic;

import apsd.interfaces.containers.collections.Set;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

abstract public class WSetITest extends WSetTest<Long> {

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
  @DisplayName("WSet Basics")
  public class WSetBasics {

    @Test
    @DisplayName("Check starting from an Empty WSet")
    public void Empty() {
      AddTest(6);
      NewEmptyContainer();
      TestExists(0L, false);
      TestFilter(dat -> false);
      TestUnion(ThisContainer());
      TestIntersection(ThisContainer());
      TestDifference(ThisContainer());
      TestSize(0, false);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty WSet")
    public void NonEmpty() {
      AddTest(41);
      NewNonEmptyContainer();
      Set<Long> newcon = GetNewEmptyContainer();
      newcon.Insert(3L);
      newcon.Insert(1L);
      newcon.Insert(5L);
      TestInsert(-1L, true);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(5L, false);
      TestUnion(newcon);
      TestExists(1L, true);
      TestExists(3L, true);
      TestExists(5L, true);
      TestSize(7, false);
      TestDifference(newcon);
      TestExists(1L, false);
      TestExists(3L, false);
      TestExists(5L, false);
      TestSize(4, false);
      TestUnion(newcon);
      TestExists(1L, true);
      TestExists(3L, true);
      TestExists(5L, true);
      TestSize(7, false);
      TestIntersection(newcon);
      TestExists(-1L, false);
      TestExists(2L, false);
      TestSize(3, false);
      TestInsert(4L, true);
      TestInsert(0L, true);
      TestInsert(4L, false);
      TestInsert(3L, false);
      TestInsert(1L, false);
      TestInsert(3L, false);
      TestInsert(2L, true);
      TestInsert(0L, false);
      TestInsert(5L, false);
      TestPrintContent("");
      TestSize(6, false);
      TestFilter(dat -> dat % 2 == 0);
      TestSize(3, false);
      TestExists(5L, false);
      TestExists(2L, true);
      TestClear();
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("WSet Edge Cases")
  public class WSetEdgeCases {

    @Test
    @DisplayName("Single element set")
    public void SingleElement() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(42L, true);
      TestSize(1, false);
      TestExists(42L, true);
      TestExists(0L, false);
      TestInsert(42L, false);
      TestSize(1, false);
      TestRemove(42L, true);
      TestIsEmpty(true, false);
      TestExists(42L, false);
      TestRemove(42L, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Duplicate insert rejection")
    public void DuplicateRejection() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(1L, false);
      TestInsert(2L, false);
      TestInsert(3L, false);
      TestSize(3, false);
      TestInsert(1L, false);
      TestInsert(1L, false);
      TestInsert(1L, false);
      TestSize(3, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove non-existent element")
    public void RemoveNonExistent() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(5L, true);
      TestRemove(2L, false);
      TestRemove(4L, false);
      TestRemove(0L, false);
      TestRemove(100L, false);
      TestSize(3, false);
      TestExists(1L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with empty set")
    public void UnionWithEmpty() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> emptySet = GetNewEmptyContainer();
      TestUnion(emptySet);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with disjoint set")
    public void UnionWithDisjoint() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(4L);
      other.Insert(5L);
      other.Insert(6L);
      TestUnion(other);
      TestSize(6, false);
      TestExists(1L, true);
      TestExists(4L, true);
      TestExists(6L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with overlapping set")
    public void UnionWithOverlapping() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(4L);
      TestUnion(other);
      TestSize(4, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(4L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Intersection with empty set")
    public void IntersectionWithEmpty() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> emptySet = GetNewEmptyContainer();
      TestIntersection(emptySet);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Intersection with disjoint set")
    public void IntersectionWithDisjoint() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(4L);
      other.Insert(5L);
      other.Insert(6L);
      TestIntersection(other);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestExists(4L, false);
    }

    @Test
    @DisplayName("Intersection with overlapping set")
    public void IntersectionWithOverlapping() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(5L);
      TestIntersection(other);
      TestSize(2, false);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(1L, false);
      TestExists(4L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Difference with empty set")
    public void DifferenceWithEmpty() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> emptySet = GetNewEmptyContainer();
      TestDifference(emptySet);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Difference with disjoint set")
    public void DifferenceWithDisjoint() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(4L);
      other.Insert(5L);
      other.Insert(6L);
      TestDifference(other);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
    }

    @Test
    @DisplayName("Difference with overlapping set")
    public void DifferenceWithOverlapping() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(5L);
      TestDifference(other);
      TestSize(2, false);
      TestExists(1L, true);
      TestExists(4L, true);
      TestExists(2L, false);
      TestExists(3L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Difference with superset")
    public void DifferenceWithSuperset() {
      AddTest(7);
      NewEmptyContainer();
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(1L);
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(4L);
      TestDifference(other);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(2L, false);
      TestExists(3L, false);
    }

    @Test
    @DisplayName("Filter keeps matching elements")
    public void FilterKeepsMatching() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestInsert(6L, true);
      TestFilter(dat -> dat % 2 == 0);
      TestSize(3, false);
      TestExists(2L, true);
      TestExists(4L, true);
      TestExists(6L, true);
      TestExists(1L, false);
      TestExists(3L, false);
      TestExists(5L, false);
    }

    @Test
    @DisplayName("Filter removes all")
    public void FilterRemovesAll() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFilter(dat -> dat > 100L);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestExists(2L, false);
    }

    @Test
    @DisplayName("Filter keeps all")
    public void FilterKeepsAll() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFilter(dat -> dat > 0L);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Negative numbers in set")
    public void NegativeNumbers() {
      AddTest(15);
      NewEmptyContainer();
      TestInsert(-10L, true);
      TestInsert(-5L, true);
      TestInsert(0L, true);
      TestInsert(5L, true);
      TestInsert(10L, true);
      TestSize(5, false);
      TestExists(-10L, true);
      TestExists(-5L, true);
      TestExists(0L, true);
      TestInsert(-10L, false);
      TestInsert(0L, false);
      TestRemove(-5L, true);
      TestExists(-5L, false);
      TestSize(4, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Clear and reuse")
    public void ClearAndReuse() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestSize(3, false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestSize(2, false);
      TestExists(10L, true);
      TestExists(20L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with self")
    public void UnionWithSelf() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestUnion(ThisContainer());
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
    }

    @Test
    @DisplayName("Intersection with self")
    public void IntersectionWithSelf() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestIntersection(ThisContainer());
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
    }

    @Test
    @DisplayName("Difference with self")
    public void DifferenceWithSelf() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestDifference(ThisContainer());
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Large set operations")
    public void LargeSetOperations() {
      AddTest(108);
      NewEmptyContainer();
      for (long i = 1L; i <= 50L; i++) {
        TestInsert(i, true);
      }
      TestSize(50, false);
      for (long i = 1L; i <= 50L; i++) {
        TestInsert(i, false);
      }
      TestSize(50, false);
      TestExists(1L, true);
      TestExists(25L, true);
      TestExists(50L, true);
      TestExists(51L, false);
      TestFilter(dat -> dat <= 25L);
      TestSize(25, false);
    }

    @Test
    @DisplayName("Symmetric difference behavior")
    public void SymmetricDifferenceBehavior() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(4L);
      // Simulate symmetric difference: (A - B) ∪ (B - A)
      // First get A - B
      TestDifference(other);
      TestSize(1, false);
      TestExists(1L, true);
      TestExists(2L, false);
      TestExists(3L, false);
      // Now we have {1}, need to add {4} (B - A)
      Set<Long> bMinusA = GetNewEmptyContainer();
      bMinusA.Insert(4L);
      TestUnion(bMinusA);
      TestSize(2, false);
      TestExists(1L, true);
      TestExists(4L, true);
      TestPrintContent("");
    }

  }

  @Nested
  @DisplayName("WSet Exception Handling")
  public class WSetExceptionHandling {

    @Test
    @DisplayName("Insert/Remove/Exists with null element")
    public void NullElementOps() {
      AddTest(3);
      NewNonEmptyContainer();
      assertFalse(ThisContainer().Insert(null));
      assertFalse(ThisContainer().Remove(null));
      assertFalse(ThisContainer().Exists(null));
    }

    @Test
    @DisplayName("Filter(null) is safe (no crash)")
    public void FilterNullPredicateSafe() {
      AddTest(1);
      NewNonEmptyContainer();
      assertDoesNotThrow(() -> ThisContainer().Filter(null));
    }
  }

  @Nested
  @DisplayName("WSet Contract Tests")
  public class WSetContractTests {

    @Test
    @DisplayName("Union/Difference/Intersection with null set follow Set.java contract")
    public void NullSetContract() {
      AddTest(12);

      // Union(null) and Difference(null) are no-ops; Intersection(null) clears.
      NewNonEmptyContainer();
      long beforeSize = ThisContainer().Size().ToLong();

      assertDoesNotThrow(() -> ThisContainer().Union(null));
      assertEquals(beforeSize, ThisContainer().Size().ToLong(), "Union(null) should not change size");

      assertDoesNotThrow(() -> ThisContainer().Difference(null));
      assertEquals(beforeSize, ThisContainer().Size().ToLong(), "Difference(null) should not change size");

      assertDoesNotThrow(() -> ThisContainer().Intersection(null));
      assertEquals(0L, ThisContainer().Size().ToLong(), "Intersection(null) should clear the set");
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Set operations must not mutate the argument set")
    public void ArgumentNotMutated() {
      AddTest(18);

      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);

      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(99L);

      long otherBeforeSize = other.Size().ToLong();
      boolean otherHad2 = other.Exists(2L);
      boolean otherHad99 = other.Exists(99L);

      ThisContainer().Union(other);
      assertEquals(otherBeforeSize, other.Size().ToLong(), "Union should not mutate argument size");
      assertEquals(otherHad2, other.Exists(2L), "Union should not mutate argument membership");
      assertEquals(otherHad99, other.Exists(99L), "Union should not mutate argument membership");

      ThisContainer().Difference(other);
      assertEquals(otherBeforeSize, other.Size().ToLong(), "Difference should not mutate argument size");
      assertEquals(otherHad2, other.Exists(2L), "Difference should not mutate argument membership");
      assertEquals(otherHad99, other.Exists(99L), "Difference should not mutate argument membership");

      // Rebuild A to ensure Intersection has work to do
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);

      ThisContainer().Intersection(other);
      assertEquals(otherBeforeSize, other.Size().ToLong(), "Intersection should not mutate argument size");
      assertEquals(otherHad2, other.Exists(2L), "Intersection should not mutate argument membership");
      assertEquals(otherHad99, other.Exists(99L), "Intersection should not mutate argument membership");
    }

    @Test
    @DisplayName("IsEqual is reflexive and symmetric on identical content")
    public void IsEqualReflexiveAndSymmetric() {
      AddTest(10);

      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);

      assertTrue(ThisContainer().IsEqual(ThisContainer()), "IsEqual should be reflexive");

      Set<Long> copy = GetNewEmptyContainer();
      copy.Insert(10L);
      copy.Insert(20L);
      copy.Insert(30L);

      assertTrue(ThisContainer().IsEqual(copy), "IsEqual should be true for identical content");
      assertTrue(copy.IsEqual(ThisContainer()), "IsEqual should be symmetric for identical content");

      copy.Insert(40L);
      assertFalse(ThisContainer().IsEqual(copy), "IsEqual should be false if sizes differ");
      assertFalse(copy.IsEqual(ThisContainer()), "IsEqual should be false if sizes differ (symmetric)");
    }

    @Test
    @DisplayName("InsertAll/InsertSome semantics with null and duplicates")
    public void InsertAllInsertSomeContract() {
      AddTest(14);

      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      long beforeSize = ThisContainer().Size().ToLong();

      assertTrue(ThisContainer().InsertAll(null), "InsertAll(null) should return true (no-op)");
      assertEquals(beforeSize, ThisContainer().Size().ToLong(), "InsertAll(null) should not change size");

      assertFalse(ThisContainer().InsertSome(null), "InsertSome(null) should return false (no-op)");
      assertEquals(beforeSize, ThisContainer().Size().ToLong(), "InsertSome(null) should not change size");

      // Deterministic success path: all new elements
      Set<Long> srcAllNew = GetNewEmptyContainer();
      srcAllNew.Insert(3L);
      srcAllNew.Insert(4L);

      assertTrue(ThisContainer().InsertAll(srcAllNew), "InsertAll should be true when all insertions succeed");
      assertTrue(ThisContainer().Exists(3L));
      assertTrue(ThisContainer().Exists(4L));
      assertEquals(beforeSize + 2, ThisContainer().Size().ToLong(), "Size should increase by number of new elements");

      // Deterministic failure path: all duplicates
      long afterAllNewSize = ThisContainer().Size().ToLong();
      Set<Long> srcAllDuplicates = GetNewEmptyContainer();
      srcAllDuplicates.Insert(1L);
      srcAllDuplicates.Insert(2L);

      assertFalse(ThisContainer().InsertAll(srcAllDuplicates), "InsertAll should be false if any insertion fails (duplicates)");
      assertEquals(afterAllNewSize, ThisContainer().Size().ToLong(), "InsertAll(duplicates) should not change size");

      assertFalse(ThisContainer().InsertSome(srcAllDuplicates), "InsertSome should be false if nothing new is inserted");

      // Deterministic InsertSome success: source has exactly one new element
      Set<Long> srcOneNew = GetNewEmptyContainer();
      srcOneNew.Insert(5L);

      assertTrue(ThisContainer().InsertSome(srcOneNew), "InsertSome should be true if at least one element is inserted");
      assertTrue(ThisContainer().Exists(5L));
      assertEquals(afterAllNewSize + 1, ThisContainer().Size().ToLong(), "Size should increase by 1");
    }

    @Test
    @DisplayName("RemoveAll/RemoveSome contract with null and non-existent elements")
    public void RemoveAllRemoveSomeContract() {
      AddTest(12);

      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      long beforeSize = ThisContainer().Size().ToLong();

      assertTrue(ThisContainer().RemoveAll(null), "RemoveAll(null) should return true (no-op)");
      assertEquals(beforeSize, ThisContainer().Size().ToLong(), "RemoveAll(null) should not change size");

      assertFalse(ThisContainer().RemoveSome(null), "RemoveSome(null) should return false (no-op)");
      assertEquals(beforeSize, ThisContainer().Size().ToLong(), "RemoveSome(null) should not change size");

      // Remove some existing elements
      Set<Long> toRemove = GetNewEmptyContainer();
      toRemove.Insert(2L);
      toRemove.Insert(3L);

      assertTrue(ThisContainer().RemoveAll(toRemove), "RemoveAll should be true when all removals succeed");
      assertFalse(ThisContainer().Exists(2L));
      assertFalse(ThisContainer().Exists(3L));
      assertEquals(2, ThisContainer().Size().ToLong(), "Size should decrease by number of removed elements");

      // Try to remove non-existent elements
      Set<Long> nonExistent = GetNewEmptyContainer();
      nonExistent.Insert(99L);
      nonExistent.Insert(100L);

      assertFalse(ThisContainer().RemoveAll(nonExistent), "RemoveAll should be false if any removal fails");
      assertFalse(ThisContainer().RemoveSome(nonExistent), "RemoveSome should be false if nothing is removed");
    }

    @Test
    @DisplayName("Filter with edge predicates")
    public void FilterEdgePredicates() {
      AddTest(8);

      NewEmptyContainer();
      for (long i = 0; i < 10; i++) {
        TestInsert(i, true);
      }

      // Filter that keeps only boundary values
      TestFilter(dat -> dat == 0L || dat == 9L);
      TestSize(2, false);
      TestExists(0L, true);
      TestExists(9L, true);
      TestExists(5L, false);

      // Rebuild and test with complex predicate
      NewEmptyContainer();
      for (long i = 1; i <= 20; i++) {
        TestInsert(i, true);
      }
      TestFilter(dat -> dat % 2 == 0 && dat % 3 == 0); // divisible by both 2 and 3 (i.e., by 6)
      TestSize(3, false); // 6, 12, 18
      TestExists(6L, true);
      TestExists(12L, true);
      TestExists(18L, true);
    }
  }

}
