package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data> {

    Data Top();

    void Pop();

    default Data TopNPop(){
        if(this.IsEmpty()) return null;
        Data top = Top();
        Pop();
        return top;
    }
    default void SwapTop(Data dat){
        if(dat == null) return;
        if(this.IsEmpty()) return;
        Pop();
        Push(dat);
    }
    default Data TopNSwap(Data dat){
        if(dat == null) return null;
        if(this.IsEmpty()) return null;
        Data top = Top();
        Pop();
        Push(dat);
        return top;
    }
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
      if(data == null) return false;
      Push(data);
      return true;
    }

}
