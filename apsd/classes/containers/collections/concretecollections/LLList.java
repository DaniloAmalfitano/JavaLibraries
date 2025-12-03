package apsd.classes.containers.collections.concretecollections;

 import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
 import apsd.classes.containers.collections.concretecollections.bases.LLNode;
 import apsd.classes.utilities.Box;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.iterators.ForwardIterator;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.MutableSequence;

 import java.util.ListIterator;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data>{ // Must extend LLChainBase and implement List

  public LLList(){
    super();
  }

  public LLList(TraversableContainer<Data> con){
    super(con);
  }

  protected LLList(long size, LLNode<Data> head, LLNode<Data> tail){
    super(size, head, tail);
  }

    @Override
    protected LLChainBase<Data> newChain(long size, LLNode<Data> headref, LLNode<Data> tailref) {
        return new LLList<>(size, headref, tailref);
    }

    @Override
  public MutableForwardIterator<Data> FIterator() {
    return new ListFIterator();
  }
  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new ListBIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

    @Override
    public void SetAt(Data data, Natural index) {
        if(data == null){
            return;
        }
        List.super.SetAt(data, index);
    }
    @Override
    public void SetFirst(Data data){
        if(data == null){
            return;
        }
        if(headref.IsNull()) throw new IndexOutOfBoundsException("Cannot be empty!");
        headref.Get().Set(data);
    }
    @Override
    public void SetLast(Data data){
        if(data == null){
            return;
        }
        if(headref.IsNull()) throw new IndexOutOfBoundsException();
        tailref.Get().Set(data);
    }
    @Override
    public MutableSequence<Data> SubSequence(Natural startindex, Natural endindex) {
        return (MutableSequence<Data>) super.SubSequence(startindex, endindex);
    }
    @Override
    public void InsertAt(Data data, Natural index){
        if(data == null) return;
        long idx = index.ToLong();
        if(idx < 0 || idx > size.ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size.ToLong() + "!");
        if(idx == size.ToLong()) {
            LLNode<Data> newNode = new LLNode<>(data, null);
            if (headref.IsNull()) {
                headref.Set(newNode);
                tailref.Set(newNode);
            } else {
                tailref.Get().SetNext(newNode);
                tailref.Set(newNode);
            }
            size.Increment();
            return;
        }
        Box<LLNode<Data>> curr = headref;
        Box<LLNode<Data>> prev = new Box<>();
        for (long i = 0; i < idx; i++) {
            prev.Set(curr.Get());
            curr = curr.Get().GetNext();
        }
        LLNode<Data> newNode = new LLNode<>(data, curr.Get());
        if (prev.IsNull()) {
            headref.Set(newNode);
        } else {
            prev.Get().SetNext(newNode);
        }
        size.Increment();
    }
    @Override
    public void InsertFirst(Data data) {
        if (data == null) throw new NullPointerException("Data cannot be null!");
        LLNode<Data> node = new LLNode<>(data);
        if (headref.IsNull()) {
            headref.Set(node);
            tailref.Set(node);
        } else {
            node.SetNext(headref.Get());
            headref.Set(node);
        }
        size.Increment();
    }

    @Override
    public void InsertLast(Data data) {
        if (data == null) throw new NullPointerException("Data cannot be null!");
        LLNode<Data> node = new LLNode<>(data);
        if (tailref.IsNull()) {
            headref.Set(node);
            tailref.Set(node);
        } else {
            tailref.Get().SetNext(node);
            tailref.Set(node);
        }
        size.Increment();
    }


  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

}
