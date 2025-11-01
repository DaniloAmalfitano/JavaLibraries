package apsd.interfaces.containers.collections;

public interface OrderedSet<Data extends Comparable<? super Data>> extends Set<Data>{ // Must extend Set

  // Min
    default Data Min(){
       return FoldForward((data,min) -> (min == null || data.compareTo(min) < 0) ? data : min, null);
    }

  // RemoveMin
    default void RemoveMin(){
       Data min = Min();
       if(min != null)
         Remove(min);
    }

  // MinNRemove
    default Data MinNRemove(){
       Data min = Min();
       if(min != null)
         Remove(min);
       return min;
    }

  // Max
    default Data Max(){
       return FoldForward((data,max) -> (max == null || data.compareTo(max) > 0) ? data : max, null);
    }

  // RemoveMax
    default void RemoveMax(){
       Data max = Max();
       if(max != null)
         Remove(max);
    }

  // MaxNRemove
    default Data MaxNRemove(){
       Data max = Max();
       if(max != null)
         Remove(max);
       return max;
    }

  // Predecessor
  // java
  default Data Predecessor(Data data){
      if (data == null) return null;
      return FoldForward((elem, best) -> {
          if (elem.compareTo(data) < 0)
              return (best == null || elem.compareTo(best) > 0) ? elem : best;
          return best;
      }, null);
  }


    // RemovePredecessor
    default void RemovePredecessor(Data data){
        Data pred = Predecessor(data);
        if(pred != null)
          Remove(pred);
    }

  // PredecessorNRemove
    default Data PredecessorNRemove(Data data){
        Data pred = Predecessor(data);
        if(pred != null)
          Remove(pred);
        return pred;
    }

  // Successor
    default Data Successor(Data data){ //Rivedilo
        if (data == null) return null;
        return FoldForward((elem, best) -> {
            if (elem.compareTo(data) > 0)
                return (best == null || elem.compareTo(best) < 0) ? elem : best;
            return best;
        }, null);
    }

  // RemoveSuccessor
    default void RemoveSuccessor(Data data){
        Data succ = Successor(data);
        if(succ != null)
          Remove(succ);
    }

  // SuccessorNRemove
    default Data SuccessorNRemove(Data data){
        Data succ = Successor(data);
        if(succ != null)
          Remove(succ);
        return succ;
    }

}
