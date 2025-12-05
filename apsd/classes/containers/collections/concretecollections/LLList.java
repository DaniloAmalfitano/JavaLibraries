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
public class LLList<Data> extends LLChainBase<Data> implements List<Data>{

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

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

    @Override
    public MutableForwardIterator<Data> FIterator() {
        return new ListFIterator();
    }

    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return new ListBIterator();
    }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

    @Override
    public void SetAt(Data data, Natural index) {
        if (index.ToLong() >= Size().ToLong() || index.ToLong() < 0) throw new IndexOutOfBoundsException("Index: " + index);
        LLNode<Data> node = headref.Get();
        long i = 0;
        long target = index.ToLong();
        while (i < target && node != null) {
            node = node.GetNext().Get();
            i++;
        }
        if (node == null) {
            throw new IllegalStateException("Internal list structure corrupted");
        }
        node.Set(data);
    }

    @Override
    public void SetFirst(Data data) {
        if (headref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
        headref.Get().Set(data);
    }

    @Override
    public void SetLast(Data data) {
        if (tailref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
        tailref.Get().Set(data);
    }

    @Override
    public MutableSequence<Data> SubSequence(Natural startindex, Natural endindex) {
        if (startindex.ToLong() < 0 || endindex.ToLong() > size.ToLong() || startindex.ToLong() > endindex.ToLong()) {
            throw new IndexOutOfBoundsException("Invalid start or end index");
        }
        LLList<Data> subList = new LLList<Data>();
        LLNode<Data> cur = headref.Get();
        long index = 0;
        while (cur != null && index < endindex.ToLong()) {
            if (index >= startindex.ToLong()) {
                subList.InsertLast(cur.Get());
            }
            cur = cur.GetNext().Get();
            index++;
        }
        return subList;
    }


    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */

    @Override
    public void InsertAt(Data data, Natural index) {
        long idx = index.ToLong();
        if (idx < 0 || idx > size.ToLong()) throw new IndexOutOfBoundsException("Index: " + idx);
        if (idx == 0) {
            InsertFirst(data);
        } else if (idx == size.ToLong()) {
            InsertLast(data);
        } else {
            LLNode<Data> node = headref.Get();
            long i = 0;
            while (i < idx - 1 && node != null) {
                node = node.GetNext().Get();
                i++;
            }
            if(node == null) throw new IllegalStateException("Internal list structure corrupted");
            LLNode<Data> newNode = new LLNode<Data>(data);
            newNode.SetNext(node.GetNext().Get());
            node.SetNext(newNode);
            size.Increment();
        }
    }

    @Override
    public void InsertFirst(Data data) {
        LLNode<Data> newNode = new LLNode<>(data);
        newNode.SetNext(headref.Get());
        headref.Set(newNode);
        if (tailref.Get() == null) {
            tailref.Set(newNode);
        }
        size.Increment();
    }

    @Override
    public void InsertLast(Data data) {
        LLNode<Data> newNode = new LLNode<Data>(data);
        if (tailref.Get() == null) {
            headref.Set(newNode);
            tailref.Set(newNode);
        } else {
            tailref.Get().SetNext(newNode);
            tailref.Set(newNode);
        }
        size.Increment();
    }
    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */
}




