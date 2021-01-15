package Model.Type;

import Model.Value.IValue;

public interface IType {
    boolean equals(Object another);
    IValue defaultValue();
}
