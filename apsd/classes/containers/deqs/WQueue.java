package apsd.classes.containers.deqs;

 import apsd.classes.containers.collections.concretecollections.VList;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.List;
 import apsd.interfaces.containers.deqs.Queue;

/** Object: Wrapper queue implementation. */
public class WQueue<Data> implements Queue<Data>{

  protected final List<Data> lst;

   public WQueue(){
       this.lst = new VList<>();
   }

   public WQueue(List<Data> lst){
       if (lst == null) throw new NullPointerException("List cannot be null!");
       this.lst = lst;
   }


    public WQueue(TraversableContainer<Data> con){
        this.lst = new VList<>();
        if (con != null) {
            con.TraverseForward(dat -> {
                if (dat != null) this.lst.InsertLast(dat);
                return false;
            });
        }
    }

    public WQueue(List<Data> lst, TraversableContainer<Data> con) {
        if (lst == null) throw new NullPointerException("List cannot be null!");
        this.lst = lst;
        if (con != null) {
            con.TraverseForward(dat -> {
                if (dat != null) this.lst.InsertLast(dat);
                return false;
            });
        }
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
  /* Override specific member functions from Queue                            */
  /* ************************************************************************ */

    @Override
    public Data Head() {
        if (Size().IsZero()) return null;
        return lst.GetFirst();
    }

    @Override
    public void Dequeue() {
        if (Size().IsZero()) return;
        lst.RemoveFirst();
    }

    @Override
    public Data HeadNDequeue() {
        if (Size().IsZero()) return null;
        Data head = Head();
        Dequeue();
        return head;
    }

    @Override
    public void Enqueue(Data dat) {
        if (dat == null) return;
        lst.InsertLast(dat);
    }
}
