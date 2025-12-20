package apsd.classes.containers.deqs;

 import apsd.classes.containers.collections.concretecollections.VList;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<Data>{

  protected final List<Data> lst;

   public WStack(){
    this.lst = new VList<>();
   }

   public WStack(List<Data> lst){
    if(lst == null) throw new NullPointerException("List cannot be null!");
    this.lst = lst;
   }

   public WStack(TraversableContainer<Data> con){
       if(con == null) throw new NullPointerException("Container cannot be null!");
       this.lst = new VList<>(con);
   }

   public WStack(List<Data> lst, TraversableContainer<Data> con){
       if(lst == null || con == null) throw new NullPointerException("List and Container cannot be null!");
       this.lst = lst;
        con.TraverseForward(dat -> {this.lst.Insert(dat); return false;});
   }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

   @Override
   public Natural Size() {
        return lst.Size();
   }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

   @Override
   public void Clear() {
         lst.Clear();
   }

  /* ************************************************************************ */
  /* Override specific member functions from Stack                            */
  /* ************************************************************************ */

  @Override
  public Data Top() {
      if (lst.IsEmpty()) {
          return null;
      }
      return lst.GetFirst();
  }
  @Override
   public void Pop() {
       if (!lst.IsEmpty()) {
           lst.RemoveFirst();
       }
   }
   @Override
   public Data TopNPop(){
       Data top = Top();
       Pop();
       return top;
   }
   @Override
   public void SwapTop(Data dat){
      if(this.IsEmpty()) return;
      Pop();
      Push(dat);
   }
   @Override
   public Data TopNSwap(Data dat){
         Data top = Top();
         SwapTop(dat);
         return top;
   }
   @Override
   public void Push(Data data) {
      if(data == null) throw new IllegalArgumentException("Data cannot be null!");
      lst.InsertFirst(data);
   }
}
