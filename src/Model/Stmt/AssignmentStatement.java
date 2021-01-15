package Model.Stmt;

import Exceptions.MyDictionaryException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyStack;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;

public class AssignmentStatement implements IStatement{
    private String id;
    private IExpression expression;

    public AssignmentStatement(String var, IExpression exp){
        id = var;
        expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        MyStack<IStatement> stack = state.getExecutionStack();
        MyDictionary<String, IValue> symbolTable=state.getSymbolTable();
        if(symbolTable.isDefined(id)){
            IValue value = expression.evaluate(symbolTable);
            IType idType = symbolTable.lookup(id).getType();
            if(value.getType().equals(idType)) symbolTable.update(id, value);
            else throw new RuntimeException();
        }
        else throw new MyDictionaryException("Variable <" + id + "> not defined");
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(id, expression);
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        IType typVar = typeEnv.lookup(id);
        IType typExp = expression.typecheck(typeEnv);

        if(typVar.equals(typExp)){
            return typeEnv;
        }
        else
        {
            System.out.println("Assignment: first argument and second argument not of the same type");
        }

        return null;
    }


}
