package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data> { // Must extend ClearableContainer and InsertableContainer

  // Top
    Data Top();
  // Pop
    void Pop();
  // TopNPop
    default Data TopNPop(){
      Data top = Top();
      Pop();
      return top;
    }
  // SwapTop
    default void SwapTop(){
        Data top = Top();
        Pop();
        Push(top);
    }
  // TopNSwap
    default Data TopNSwap(Data dat){
        Data top = Top();
        Pop();
        Push(dat);
        return top;
    }

  // Push
    void Push(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    @Override
    default void Clear(){
        while(!IsEmpty()){
            Pop();
        }
    }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
    default boolean Insert(Data data){
        Push(data);
        return true;
    }

}
