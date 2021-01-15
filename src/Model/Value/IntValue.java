package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue{
    private int value;
    public IntValue(int v){
        setValue(v);
    }

    public void setValue(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }

    @Override
    public IType getType() {
        return new IntType();
    }
}
