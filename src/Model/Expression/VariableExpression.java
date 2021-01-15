package Model.Expression;

import Exceptions.ExpressionException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;

public class VariableExpression implements IExpression{
    private String id;

    public VariableExpression(String variable){
        id = variable;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> table, MyHeap<Integer, IValue> hp) throws ExpressionException {
        return table.lookup(id);
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        return typeEnv.lookup(id);
    }
}
