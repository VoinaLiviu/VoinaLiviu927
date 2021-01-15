package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement{
    IExpression expression;
    String varName;

    public ReadFile(IExpression exp, String vName){
        expression = exp;
        varName = vName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        if (!state.getSymbolTable().isDefined(varName)){
            System.out.println("Variable not defined!");
            return state;
        }
        if(state.getSymbolTable().isDefined(varName)){
            if (!state.getSymbolTable().lookup(varName).getType().equals(new IntType())){
                System.out.println("Not of int type!");
                return state;
            }
        }
        IValue evaluatedExpression = expression.evaluate(state.getSymbolTable());
        if (!evaluatedExpression.getType().equals(new StringType())){
            System.out.println("Not of String Type!");
            return state;
        }
        StringValue stringExpression = (StringValue)evaluatedExpression;
        String stringValue = stringExpression.getValue();
        if (!state.getFileTable().isDefined(stringValue)){
            System.out.println("Not in File Table");
            return state;
        }

        BufferedReader reader = state.getFileTable().lookup(stringValue);

        try {
            String fileLine = reader.readLine();
            if (fileLine.equals("")){
                IntValue value = new IntValue(0);
                state.getSymbolTable().update(varName, value);
            }
            else{
                int integerVal = Integer.parseInt(fileLine);
                IntValue value = new IntValue(integerVal);
                state.getSymbolTable().update(varName, value);
            }
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
