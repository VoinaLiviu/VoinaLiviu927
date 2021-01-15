package Model.Value;

import Model.Type.IType;
import Model.Type.StringType;

public class StringValue implements IValue{
    private String value;

    public StringValue(String val){
        setValue(val);
    }

    public void setValue(String val){
        value = val;
    }

    public String getValue(){
        return value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }
}
