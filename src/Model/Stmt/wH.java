package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.RefValue;

public class wH implements IStatement{
    private String varName;
    private IExpression expression;

    public wH(String vName, IExpression exp){
        varName = vName;
        expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        if (state.getSymbolTable().isDefined(varName)){
            if (state.getSymbolTable().lookup(varName) instanceof RefValue){
                IValue varAddress = state.getSymbolTable().lookup(varName);
                if (varAddress.getType().equals(new IntType())){
                    IntValue intVarAddress = (IntValue) varAddress;
                    Integer varAddressValue = intVarAddress.getValue();
                    if (state.getHeap().isDefined(varAddressValue)){
                        IValue evaluatedExpression = expression.evaluate(state.getSymbolTable(), state.getHeap());
                        if (evaluatedExpression.getType().equals(state.getSymbolTable().lookup(varName).getType())){
                            state.getHeap().update(varAddressValue,evaluatedExpression);
                        }
                        else{
                            System.out.println("Types don't match!");
                            return state;
                        }
                    }
                    else{
                        System.out.println("Variable not in heap!");
                        return state;
                    }
                }
                else{
                    System.out.println("Types don't match!");
                    return state;
                }
            }
            else{
                System.out.println("Variable not of RefValue type!");
                return state;
            }
        }
        else {
            System.out.println("Variable isn't defined!");
            return state;
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
