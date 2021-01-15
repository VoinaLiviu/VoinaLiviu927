package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class New implements IStatement{
    private String varName;
    private IExpression expression;

    public New(String vName, IExpression exp){
        varName = vName;
        expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        if (state.getSymbolTable().isDefined(varName)){
            if (!state.getSymbolTable().lookup(varName).getType().equals(new RefType())){
                System.out.println("Variable not of RefType type!");
                return state;
            }
        }
        else{
            System.out.println("Variable not defined!");
            return state;
        }

        IValue evaluatedExpression = expression.evaluate(state.getSymbolTable(),state.getHeap());
        if (!evaluatedExpression.getType().equals(state.getSymbolTable().lookup(varName).getType())){
            System.out.println("Types don't match!");
            return state;
        }

        //to be continued :)

         return state;
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typVar = typeEnv.lookup(varName);
        IType typExp = expression.typecheck(typeEnv);
        if(typVar.equals(new RefType(typExp))){
            return typeEnv;
        }
        else {
            System.out.println("NEW statement: right hand side and left hand side have different types");
        }
        return null;
    }
}
