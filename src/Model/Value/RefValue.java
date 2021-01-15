package Model.Value;

import Model.Type.IType;
import Model.Type.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;

    public RefValue(int addr, IType locType){
        address = addr;
        locationType = locType;
    }

    public int getAddress() { return address; }

    public IType getLocationType() { return locationType; }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }
}
