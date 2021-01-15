package Model.Stmt;

import Model.ADTs.MyDictionary;
import Model.ADTs.MyStack;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;

import java.util.HashMap;
import java.util.Map;

public class forkStatement implements IStatement{
    IStatement statement;

    public forkStatement(IStatement stmt){
        statement = stmt;
    }
    @Override

    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws RuntimeException {
        MyDictionary<String, IValue> newSymbolTable = new MyDictionary<>();
        for(Map.Entry<String, IValue> entry : state.getSymbolTable().getContent().entrySet()){
            newSymbolTable.update(entry.getKey(),entry.getValue());
        }
        ProgramState newProgramState = new ProgramState(new MyStack<>(), newSymbolTable, state.getOutputList(),  this.statement, state.getFileTable(), state.getHeap());
        newProgramState.getId();
        return newProgramState;
    }

    @Override
    public IStatement deepCopy() {
        return new forkStatement(this.statement.deepCopy());
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        return statement.typecheck(typeEnv);
    }
}
