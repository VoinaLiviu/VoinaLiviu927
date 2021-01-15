package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.ADTs.MyStack;
import Model.ProgramState.ProgramState;
import Model.Type.IType;

public class CompoundStatement implements IStatement{
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement firstStatement, IStatement secondStatement){
        first = firstStatement;
        second = secondStatement;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        MyStack<IStatement> stack=state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(second, first);
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
