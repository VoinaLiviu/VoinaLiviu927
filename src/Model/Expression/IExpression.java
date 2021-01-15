package Model.Expression;

import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;

public interface IExpression {
    IValue evaluate(MyDictionary<String, IValue> table, MyHeap<Integer, IValue> hp) throws RuntimeException;
    IType typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException;
}
