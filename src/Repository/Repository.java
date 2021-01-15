package Repository;

import Model.ADTs.IStack;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.ProgramState.ProgramState;
import Model.Stmt.IStatement;
import Model.Value.IValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private String logFilePath;

    public Repository() {
        programStates = new ArrayList<ProgramState>();
        logFilePath = "";
    }

    @Override
    public void setLogFilePath(String filePath) {
        logFilePath = filePath;
    }

    @Override
    public void addProgramState(ProgramState programState) {
        programStates.add(programState);
    }

    @Override
    public ProgramState getCrtPrg() {
        return programStates.get(0);
    }

    @Override
    public void logPrgStateExec(ProgramState programState) throws RuntimeException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            //ProgramState programState = programStates.popFirst();
            //MyStack<IStatement> executionStack = programState.getExecutionStack();
            //MyDictionary<String, IValue> symbolTable = programState.getSymbolTable();
            //MyList<IValue> outputList = programState.getOutputList();
            logFile.println(programState.toString());
            logFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public List<ProgramState> getPrgList() {
        return programStates;
    }

    @Override
    public void setPrgList(List<ProgramState> prgList) {
        programStates = prgList;
    }
}
