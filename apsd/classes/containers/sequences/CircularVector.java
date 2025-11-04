package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete (static) circular vector implementation. */
public class CircularVector<Data> extends CircularVectorBase<Data>{ // Must extend CircularVectorBase

  public CircularVector(){
      super();
  }

  public CircularVector(Natural inisize){
        super.ArrayAlloc(inisize);
  }

  public CircularVector(TraversableContainer<Data> con){
        long size = con.Size().ToLong();
        if(size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
        super.ArrayAlloc(Natural.Of(size));
        Box<Natural> index = new Box<>(Natural.Of(0));
        con.TraverseForward(elem -> {
            arr[(int)index.Get().ToLong()] = elem;
            index.Set(Natural.Of(index.Get().ToLong() + 1L));
            return false;
        });
  }

  protected CircularVector(Data[] arr){
        super(arr);
  }

  // NewVector
    @Override
    protected CircularVector<Data> NewVector(Data[] arr){
        return new CircularVector<>(arr);
    }
    @Override
    public Natural Capacity() {
      return super.Capacity();
    }
    @Override
    public Data GetAt(Natural index){
        return super.GetAt(index);
    }
    @Override
    public void SetAt(Data element, Natural index){
        super.SetAt(element, index);
    }
    @Override
    public void ShiftLeft(Natural idx, Natural howMany){
        super.ShiftLeft(idx, howMany);
    }
    @Override
    public void ShiftRight(Natural idx, Natural howMany){
        super.ShiftRight(idx, howMany);
    }
    @Override
    public void ArrayAlloc(Natural newsize) {
      super.ArrayAlloc(newsize);
    }
    @Override
    public boolean Exists(Data element){
        Box<Natural> index = new Box<>(Natural.Of(0));
        return this.TraverseForward(elem -> {
            if((element == null && elem == null) || (element != null && element.equals(elem))){
                return true;
            }
            index.Set(Natural.Of(index.Get().ToLong() + 1L));
            return false;
        });
    }

}
