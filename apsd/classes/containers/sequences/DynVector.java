package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<Data>{
  public DynVector(){
      super();
  }

  public DynVector(Natural inisize){
      super(inisize);
  }

  public DynVector(TraversableContainer<Data> con){
        long size = con.Size().ToLong();
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
    public boolean Exists(Data dat){
        Box<Natural> index = new Box<>(Natural.Of(0));
        return this.TraverseForward(elem -> {
            if((dat == null && elem == null) || (dat != null && dat.equals(elem))){
                return true;
            }
            index.Set(Natural.Of(index.Get().ToLong() + 1L));
            return false;
        });
    }

}
