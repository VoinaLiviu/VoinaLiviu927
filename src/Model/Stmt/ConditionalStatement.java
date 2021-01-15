package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.BooleanType;
import Model.Type.IType;
import Model.Value.IValue;

public class ConditionalStatement implements IStatement{
    private IExpression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public ConditionalStatement(IExpression conditionalExpression, IStatement thenStmt, IStatement elseStmt){
        expression = conditionalExpression;
        thenStatement = thenStmt;
        elseStatement = elseStmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        IType conditionType = expression.evaluate(state.getSymbolTable()).getType();
        if(!conditionType.equals(new BooleanType())){
            throw new RuntimeException();
        }
        else{
            IValue condition = expression.evaluate(state.getSymbolTable());
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new ConditionalStatement(expression, thenStatement, elseStatement);
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typExp = expression.typecheck(typeEnv);

        if(typExp.equals(new BooleanType())){
            thenStatement.typecheck(typeEnv);
            elseStatement.typecheck(typeEnv);
            return typeEnv;
        }
        else {
            System.out.println("The condition of IF is not of BooleanType");
        }
        return null;
    }
}
