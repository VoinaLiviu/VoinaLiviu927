package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement{
    IExpression expression;

    public CloseRFile(IExpression exp){
        expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        IValue evaluatedExpression = expression.evaluate(state.getSymbolTable());
        if (!evaluatedExpression.getType().equals(new StringType())){
            System.out.println("Not of String Type!");
            return state;
        }
        StringValue stringExpression = (StringValue) evaluatedExpression;
        String expressionValue = stringExpression.getValue();

        if (!state.getFileTable().isDefined(expressionValue)){
            System.out.println("Not in File Table!");
            return state;
        }

        BufferedReader fileHandle = state.getFileTable().lookup(expressionValue);
        try {
            fileHandle.close();
            state.getFileTable().delete(expressionValue);
            return state;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return state;
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        return null;
    }
}
