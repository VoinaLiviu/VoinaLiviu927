package Model.Value;

import Model.Type.BooleanType;
import Model.Type.IType;

public class BoolValue implements IValue{
    private boolean value;

    public BoolValue(boolean v){
        setValue(v);
    }

    public void setValue(boolean v){
        value = v;
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public IType getType() {
        return new BooleanType();
    }
}
