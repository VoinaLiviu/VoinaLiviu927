package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.ProgramState.ProgramState;
import Model.Type.IType;

public class NoOperationStatement implements IStatement{

    @Override
    public ProgramState execute(ProgramState state) throws RuntimeException {
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        return null;
    }
}
