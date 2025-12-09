package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
import apsd.classes.containers.sequences.abstractbases.VectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic circular vector implementation. */
public class DynCircularVector<Data> extends DynCircularVectorBase<Data>{ // Must extend DynCircularVectorBase

  public DynCircularVector(){
      super();
  }

  public DynCircularVector(Natural inisize){
      super(inisize.ToLong());
  }

  public DynCircularVector(TraversableContainer<Data> con){
      super(con);
  }


  protected DynCircularVector(Data[] arr){
    super(arr);
  }

  @Override
  public DynCircularVector<Data> NewVector (Data[] arr){
        return new DynCircularVector<>(arr);
    }

}
