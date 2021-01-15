package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.ProgramState.ProgramState;
import Model.Type.IType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws  RuntimeException;
    IStatement deepCopy();
    MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException;
}
