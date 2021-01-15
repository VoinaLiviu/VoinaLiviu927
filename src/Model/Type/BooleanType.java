package Model.Type;

import Model.Value.BoolValue;
import Model.Value.IValue;

public class BooleanType implements IType{
    public boolean equals(Object another){
        return another instanceof BooleanType;
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }

    public String toString(){
        return "bool";
    }
}
