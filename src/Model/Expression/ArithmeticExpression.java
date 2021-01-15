package Model.Expression;

import Exceptions.DivisionByZero;
import Exceptions.ExpressionException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class ArithmeticExpression implements IExpression{
    private IExpression expression1;
    private IExpression expression2;
    char operation; //1 -> '+'; 2 -> '-'; 3 -> '*'; 4 -> '/'

    public ArithmeticExpression(char op, IExpression exp1, IExpression exp2){
        expression1 = exp1;
        expression2 = exp2;
        operation = op;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> table, MyHeap<Integer, IValue> hp) throws DivisionByZero, ExpressionException {
        IValue value1 = expression1.evaluate(table, hp);
        IValue value2 = expression2.evaluate(table, hp);
        if(value1.getType().equals(new IntType())){
            if(value2.getType().equals(new IntType())){
                IntValue i1 = (IntValue)value1;
                IntValue i2 = (IntValue)value2;
                int n1 = i1.getValue();
                int n2 = i2.getValue();
                if(operation == 1) return new IntValue(n1 + n2);
                if(operation == 2) return new IntValue(n1 - n2);
                if(operation == 3) return new IntValue(n1 * n2);
                if(operation == 4) {
                    if(n2 == 0) throw new DivisionByZero("Trying to divide by 0. Choose another value.");
                    else return new IntValue(n1 / n2);
                }
            }
            else throw new ExpressionException("Operand's type not IntType");
        }
        else throw new ExpressionException("Operand's type not IntType");
        return new IntValue(0);
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typ1, typ2;

        typ1=expression1.typecheck(typeEnv);
        typ2=expression2.typecheck(typeEnv);

        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new IntType();
            }
            else{
                System.out.println("Second argument's type not IntType");
            }
        }
        else{
            System.out.println("First argument's type not IntType");
        }
        return null;
    }
}
