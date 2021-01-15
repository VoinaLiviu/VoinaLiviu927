package Model.Type;

import Model.Value.IValue;
import Model.Value.RefValue;

public class RefType implements IType{
    IType inner;

    public RefType(){}

    public RefType(IType innr){
        inner = innr;
    }

    public IType getInner(){ return inner; }

    public boolean equals(Object another){
        if (another instanceof RefType){
            return inner.equals(((RefType) another).getInner());
        }
        else{
            return false;
        }
    }

    public String toString(){
        return "Ref("+inner.toString()+")";
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0,inner);
    }
}
