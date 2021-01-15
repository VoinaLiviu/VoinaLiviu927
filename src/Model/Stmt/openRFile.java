package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStatement{
    private IExpression expression;

    public openRFile(IExpression exp){
        expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        IValue evaluatedExpression = expression.evaluate(state.getSymbolTable());
        IType expressionType = evaluatedExpression.getType();
        if (!expressionType.equals(new StringType())) {
            System.out.println("Not of string type!");
            return state;
        }
        StringValue stringExpression = (StringValue)evaluatedExpression;
        String expressionValue = stringExpression.getValue();
        if(state.getFileTable().isDefined(expressionValue)) {
            System.out.println("Already in File Table!");
            return state;
        }
        try {
            BufferedReader fileHandle = new BufferedReader(new FileReader(expressionValue));
            state.getFileTable().put(expressionValue, fileHandle);
        } catch (FileNotFoundException e) {
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
