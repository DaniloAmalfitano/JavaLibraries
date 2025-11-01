package apsd.classes.containers.collections.concretecollections.bases;

// import apsd.classes.containers.sequences.Vector;
// import apsd.classes.utilities.Box;
// import apsd.classes.utilities.MutableNatural;
// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.base.TraversableContainer;
// import apsd.interfaces.containers.collections.Chain;
// import apsd.interfaces.containers.iterators.BackwardIterator;
// import apsd.interfaces.containers.iterators.ForwardIterator;
// import apsd.interfaces.containers.iterators.MutableBackwardIterator;
// import apsd.interfaces.containers.iterators.MutableForwardIterator;
// import apsd.interfaces.containers.sequences.Sequence;
// import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> { // Must implement Chain

  // protected final MutableNatural size = new MutableNatural();
  // protected final Box<LLNode<Data>> headref = new Box<>();
  // protected final Box<LLNode<Data>> tailref = new Box<>();

  // LLChainBase

  // public LLChainBase(TraversableContainer<Data> con) { //Se il dato è null non va bene, modificare, questa deve essere una collezione, ma gli passo un cointainer
  //   size.Assign(con.Size());
  //   final Box<Boolean> first = new Box<>(true);
  //   con.TraverseForward(dat -> {
  //     LLNode<Data> node = new LLNode<>(dat);
  //     if (first.Get()) {
  //       headref.Set(node);
  //       first.Set(false);
  //     } else {
  //       tailref.Get().SetNext(node);
  //     }
  //     tailref.Set(node);
  //     return false;
  //   });
  // }
    // abstract protected LLChainBase<Data> newChain(long size, LLNode<Data> headref, LLNode<Data> tailref);
  // NewChain

  /* ************************************************************************ */
  /* Specific member functions from LLChainBase                               */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */
        /*
    protected class ListFrIterator(ListFRefIterator itr) implements ForwardIterator<Box<LLNode<Data>> {
    protected ForwardIterator<Box<LLNode<Data>> FrefIterator() {;
        return new ListFRefIterator();
      }
    protected class ListBRefIterator(ListBRefIterator itr) implements BackwardIterator<Box<LLNodeData>> {
    //Creo un array di riferimenti ai nodi per poter tornare indietro
      }
     */

  // @Override
  // public boolean Remove(Data dat) {
  //   if (dat == null) return false;
  //   final Box<LLNode<Data>> prd = new Box<>();
  //   return FRefIterator().ForEachForward(cur -> {
  //     LLNode<Data> node = cur.Get();
  //     if (node.Get().equals(dat)) {
  //       cur.Set(node.GetNext().Get());
  //       if (tailref.Get() == node) { tailref.Set(prd.Get()); }
  //       size.Decrement();
  //       return true;
  //     }
  //     prd.Set(node);
  //     return false;
  //   });
  // }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */
    //Per i GetFirst/Last tailred.Get().Get() Dereferenzi due volte
  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ... SubSequence


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...//AtNRemove
    /*Verifico se la posizione esista, se no restituisco un eccezione*, vedo se devo inserire in testa, coda o mezzo
        if(idx== Size.ToLong()-1) return lastNremove
        altrimenti forwarditerator
        box<LLNode<Data>> curr  ecc
        RemoveFirst
        verifico se è null, ritorno
        verifico se la testa è uguale alla coda, metto entrambi a null
        altrimenti headRef.Set(Headref.Get().GetNext().Get())
     */

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ... La filter è sbagliata //prd = node -> prd=cur.Get();


}
