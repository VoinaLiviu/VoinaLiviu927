package Model.Expression;

import Exceptions.ExpressionException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ProgramState.ProgramState;
import Model.Stmt.PrintStatement;
import Model.Type.IType;
import Model.Value.IValue;
import com.sun.jdi.Value;

public class ValueExpression implements IExpression{
    private IValue value;

    public ValueExpression(IValue val){
        value = val;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> table, MyHeap<Integer, IValue> hp) throws ExpressionException {
        return value;
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        return value.getType();
    }
}
