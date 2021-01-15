package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IType;

public class PrintStatement implements IStatement{
    private IExpression expression;

    public PrintStatement(IExpression printExpression){
        expression = printExpression;
    }

    @Override
    public String toString(){
        return expression.toString();
    }

    public ProgramState execute(ProgramState state) throws RuntimeException {
        System.out.println(expression.toString());
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression);
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }
}
