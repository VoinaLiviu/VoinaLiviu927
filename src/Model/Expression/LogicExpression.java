package Model.Expression;

import Exceptions.ExpressionException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ProgramState.ProgramState;
import Model.Type.BooleanType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import com.sun.jdi.BooleanValue;

public class LogicExpression implements IExpression{
    private IExpression expression1;
    private IExpression expression2;
    int operation; // 1 -> and; 2 -> or

    @Override
    public IValue evaluate(MyDictionary<String, IValue> table, MyHeap<Integer, IValue> hp) throws ExpressionException {
        IValue value1 = expression1.evaluate(table, hp);
        IValue value2 = expression2.evaluate(table, hp);
        if(value1.getType().equals(new BooleanType())){
            if(value2.getType().equals(new BooleanType())){
                boolean n1 = ((BoolValue) value1).getValue();
                boolean n2 = ((BoolValue) value2).getValue();

            }
            else{
                throw new ExpressionException("Operand's type not BooleanType");
            }
        }
        else{
            throw new ExpressionException("Operand's type not BooleanType");
        }
        return new BoolValue(false);
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typ1, typ2;

        typ1=expression1.typecheck(typeEnv);
        typ2=expression2.typecheck(typeEnv);

        if(typ1.equals(new BooleanType())){
            if(typ2.equals(new BooleanType())){
                return new BooleanType();
            }
            else{
                System.out.println("Second argument's type not of BooleanType");
            }
        }
        else{
            System.out.println("First argument's type not of BooleanType");
        }

        return null;
    }
}
