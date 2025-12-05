package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<Data>{ // Must extend DynLinearVectorBase
  public DynVector(){
      super();
  }

  public DynVector(Natural inisize){
      super.ArrayAlloc(inisize);
      this.size = inisize.ToLong();
  }

  public DynVector(TraversableContainer<Data> con){
        long size = con.Size().ToLong();
        if(size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
        super.ArrayAlloc(Natural.Of(size));
        this.size = size;
        Box<Natural> index = new Box<>(Natural.Of(0));
        con.TraverseForward(elem -> {
            arr[(int)index.Get().ToLong()] = elem;
            index.Set(Natural.Of(index.Get().ToLong() + 1L));
            return false;
        });
  }

  protected DynVector(Data[] arr){
        super(arr);
        this.size = arr.length;
  }

    @Override
    protected DynVector<Data> NewVector(Data[] arr){
        return new DynVector<>(arr);
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
