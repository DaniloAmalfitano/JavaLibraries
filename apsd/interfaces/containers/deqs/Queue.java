package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data> { // Must extend ClearableContainer and InsertableContainer

  Data Head();
  // Dequeue
  void Dequeue();
  // HeadNDequeue
  default Data HeadNDequeue(){
    Data head = Head();
    Dequeue();
    return head;
  }
    void Enqueue(Data data);
  // Enqueue

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    @Override
    default void Clear(){
        while(!IsEmpty()){
            Dequeue();
        }
    }
  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

    @Override
    default boolean Insert(Data data){
        Enqueue(data);
        return true;
    }
}
