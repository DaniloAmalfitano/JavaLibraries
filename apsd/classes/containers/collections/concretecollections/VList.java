package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;

 import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.iterators.MutableBackwardIterator;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;
 import apsd.interfaces.containers.sequences.DynVector;
 import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on (dynamic circular) vector. */
public class VList<Data> extends VChainBase<Data> implements List<Data>{ // Must extend VChainBase and implement List

   public VList(){}

   public VList(TraversableContainer<Data> con){
        super(con);
   }

   protected VList(DynVector<Data> vec){
        super(vec);
   }

    @Override
    protected VChainBase<Data> NewChain(DynVector<Data> vec) {
        return new VList<>(vec);
    }

    protected VList<Data> newChain(DynVector<Data> vec) {
       return new VList<>(vec);
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

    @Override
    public MutableForwardIterator<Data> FIterator() {
        return vec.FIterator(); //Dubbio immenso, probabilmente sbagliato ma mi tiltava continuare con un errore
    }
    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return vec.BIterator(); //Dubbio immenso, probabilmente sbagliato ma mi tiltava continuare con un errore
    }


    /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */
    @Override
    public void SetAt(Data data, Natural index) {
        if(data == null){
            return;
        }
        vec.SetAt(data, index); //Dubbio immenso, probabilmente sbagliato ma mi tiltava continuare con un errore, però ho meno dubbio degli iteratori
    }

    @Override
    public MutableSequence<Data> SubSequence(Natural start, Natural end) {
        return vec.SubVector(start, end); //Dubbio immenso, probabilmente sbagliato
    }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */
  @Override
  public void InsertAt(Data data, Natural index) {
      if (data == null) {
          return;
      }
      vec.InsertAt(data, index); //Dubbio immenso, probabilmente sbagliato

  }

  @Override
  public void InsertFirst(Data data) {
      if (data == null) {
          return;
      }
      InsertAt(data, Natural.ZERO);
  }
  @Override
    public void InsertLast(Data data) {
        if (data == null) {
            return;
        }
        InsertAt(data, Size().Decrement());
    }
}
