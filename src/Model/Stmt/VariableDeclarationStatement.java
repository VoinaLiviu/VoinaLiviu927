package Model.Stmt;

import Exceptions.MyDictionaryException;
import Model.ADTs.IDictionary;
import Model.ADTs.MyDictionary;
import Model.ProgramState.ProgramState;
import Model.Type.BooleanType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

import java.util.IdentityHashMap;

public class VariableDeclarationStatement implements IStatement{
    private String id;
    private IType type;

    public VariableDeclarationStatement(String var, IType varType){
        id = var;
        type = varType;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyDictionaryException {
        MyDictionary<String, IValue> table = state.getSymbolTable();
        if(table.isDefined(id)) throw new MyDictionaryException("Variable " + id + " is already defined");
        else{
            if(type.equals(new IntType())){
                table.put(id,new IntValue(0));
                state.setSymbolTable(table);
            }
            if(type.equals(new BooleanType())){
                table.put(id, new BoolValue(false));
                state.setSymbolTable(table);
            }
        }
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(id, type);
    }

    @Override
    public MyDictionary<String, IType> typecheck(MyDictionary<String, IType> typeEnv) throws RuntimeException {
        typeEnv.put(id, type);
        return typeEnv;
    }
}
