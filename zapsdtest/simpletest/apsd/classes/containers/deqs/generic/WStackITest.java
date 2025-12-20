package zapsdtest.simpletest.apsd.classes.containers.deqs.generic;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

abstract public class WStackITest extends WStackTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(6);
    NewEmptyContainer();
    TestInsert(4L, true);
    TestPush(0L);
    TestInsert(3L, true);
    TestPush(1L);
    TestInsert(2L, true);
    TestSize(5, false);
  }

  @Nested
  @DisplayName("Stack Basics")
  public class StackBasics {

    @Test
    @DisplayName("Check starting from an Empty Stack")
    public void Empty() {
      AddTest(3);
      NewEmptyContainer();
      TestTop(null);
      TestPop(true);
      TestTopNPop(null, true);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty Stack")
    public void NonEmpty() {
      AddTest(24);
      NewNonEmptyContainer();
      TestSize(5, false);
      TestTopNPop(2L, false);
      TestTop(1L);
      TestPush(5L);
      TestPush(6L);
      TestTop(6L);
      TestTopNPop(6L, false);
      TestPop(false);
      TestTop(1L);
      TestClear();
      TestTopNPop(null, true);
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      TestPush(4L);
      TestPush(5L);
      TestTop(5L);
      TestPop(false);
      TestTop(4L);
      TestTopNPop(4L, false);
      TestTop(3L);
      TestSize(3, false);
      TestPush(6L);
      TestSize(4, false);

    }
  }

  @Nested
  @DisplayName("Stack Edge Cases")
  public class StackEdgeCases {

    @Test
    @DisplayName("LIFO order verification")
    public void LIFOOrder() {
      AddTest(12);
      NewEmptyContainer();
      TestPush(10L);
      TestPush(20L);
      TestPush(30L);
      TestPush(40L);
      TestPush(50L);
      TestTopNPop(50L, false);
      TestTopNPop(40L, false);
      TestTopNPop(30L, false);
      TestTopNPop(20L, false);
      TestTopNPop(10L, false);
      TestIsEmpty(true, false);
      TestTop(null);
    }

    @Test
    @DisplayName("Single element stack")
    public void SingleElement() {
      AddTest(7);
      NewEmptyContainer();
      TestPush(42L);
      TestSize(1, false);
      TestTop(42L);
      TestPop(false);
      TestIsEmpty(true, false);
      TestTop(null);
      TestPop(true);
    }

    @Test
    @DisplayName("Push after complete pop")
    public void PushAfterEmpty() {
      AddTest(11);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPop(false);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(3L);
      TestPush(4L);
      TestSize(2, false);
      TestTop(4L);
      TestTopNPop(4L, false);
      TestTop(3L);
    }

    @Test
    @DisplayName("Large number of operations")
    public void LargeOperations() {
      AddTest(154);
      NewEmptyContainer();
      for (long i = 1L; i <= 100L; i++) {
        TestPush(i);
      }
      TestSize(100, false);
      TestTop(100L);
      for (int i = 0; i < 50; i++) {
        TestPop(false);
      }
      TestSize(50, false);
      TestTop(50L);
    }

    @Test
    @DisplayName("Alternating push and pop")
    public void AlternatingOperations() {
      AddTest(15);
      NewEmptyContainer();
      TestPush(1L);
      TestTop(1L);
      TestPush(2L);
      TestPop(false);
      TestTop(1L);
      TestPush(3L);
      TestPush(4L);
      TestPop(false);
      TestTop(3L);
      TestPop(false);
      TestTop(1L);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(5L);
      TestTop(5L);
    }

    @Test
    @DisplayName("SwapTop operations")
    public void SwapTopOperations() {
      AddTest(8);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      assertDoesNotThrow(() -> ThisContainer().SwapTop(10L));
      assertEquals(10L, ThisContainer().Top());
      assertEquals(3, ThisContainer().Size().ToLong());
      assertDoesNotThrow(() -> ThisContainer().TopNSwap(20L));
      assertEquals(20L, ThisContainer().Top());
    }

    @Test
    @DisplayName("SwapTop on empty stack")
    public void SwapTopEmpty() {
      AddTest(2);
      NewEmptyContainer();
      assertDoesNotThrow(() -> ThisContainer().SwapTop(1L));
      assertNull(ThisContainer().TopNSwap(2L));
    }

    @Test
    @DisplayName("Push null throws exception")
    public void PushNullThrows() {
      AddTest(1);
      NewEmptyContainer();
      assertThrows(IllegalArgumentException.class, () -> ThisContainer().Push(null));
    }
  }

  @Nested
  @DisplayName("Stack Constructor Edge Cases")
  public class StackConstructorEdgeCases {

    @Test
    @DisplayName("Constructor with null List throws")
    public void ConstructorNullListThrows() {
      AddTest(1);
      assertThrows(NullPointerException.class, () -> new apsd.classes.containers.deqs.WStack<Long>((apsd.interfaces.containers.collections.List<Long>) null));
    }

    @Test
    @DisplayName("Constructor with null TraversableContainer throws")
    public void ConstructorNullContainerThrows() {
      AddTest(1);
      assertThrows(NullPointerException.class, () -> new apsd.classes.containers.deqs.WStack<Long>((apsd.interfaces.containers.base.TraversableContainer<Long>) null));
    }

    @Test
    @DisplayName("Constructor with null List and TraversableContainer throws")
    public void ConstructorNullBothThrows() {
      AddTest(2);
      apsd.interfaces.containers.collections.List<Long> list = new apsd.classes.containers.collections.concretecollections.VList<>();
      apsd.classes.containers.collections.concretecollections.VList<Long> container = new apsd.classes.containers.collections.concretecollections.VList<>();
      container.Insert(1L);
      assertThrows(NullPointerException.class, () -> new apsd.classes.containers.deqs.WStack<Long>(null, container));
      assertThrows(NullPointerException.class, () -> new apsd.classes.containers.deqs.WStack<Long>(list, null));
    }
  }

}
