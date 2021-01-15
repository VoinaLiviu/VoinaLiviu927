package Model.Expression;

import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

import java.sql.Ref;

public class rH implements IExpression{
    private IExpression expression;

    public rH(IExpression exp){
        expression = exp;
    }

    @Override
    public IValue evaluate(MyDictionary<String, IValue> table, MyHeap<Integer, IValue> hp) throws RuntimeException {
        if (!(expression.evaluate(table, hp) instanceof RefValue)){
            System.out.println("Expression value not RefValue!");
            return null;
        }

        int address = ((RefValue) expression.evaluate(table, hp)).getAddress();

        if (!hp.isDefined(address)){
            System.out.println("Address not in Heap!");
            return null;
        }
        IValue value = hp.lookup(address);

        return value;
    }

    @Override
    public IType typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typ = expression.typecheck(typeEnv);

        if(typ instanceof RefType){
            RefType reft = (RefType) typ;
            return reft.getInner();
        }
        else{
            System.out.println("The rH argument is not of RefType!");
        }

        return null;
    }

}
