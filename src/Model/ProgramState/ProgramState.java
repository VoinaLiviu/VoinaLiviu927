package Model.ProgramState;

import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.Stmt.IStatement;
import Model.Value.IValue;

import javax.print.DocFlavor;
import java.io.BufferedReader;

public class ProgramState {
    private MyStack<IStatement> executionStack;
    private MyDictionary<String, IValue> symbolTable;
    private MyList<IValue> outputList;
    private IStatement originalProgram;
    private MyDictionary<String, BufferedReader> fileTable;
    private MyHeap<Integer, IValue> heap;
    private int id;
    private static int freeId = 0;

    public ProgramState(MyStack<IStatement> stack, MyDictionary<String,IValue> table, MyList<IValue> list, IStatement statement, MyDictionary<String, BufferedReader> fileTbl, MyHeap<Integer, IValue> heap1){
        executionStack = stack;
        symbolTable = table;
        outputList = list;
        originalProgram = statement.deepCopy();
        executionStack.push(statement);
        fileTable = fileTbl;
        heap = heap1;
        id = getId();
    }

    public MyStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public MyDictionary<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public MyList<IValue> getOutputList() {
        return outputList;
    }

    public MyDictionary<String, BufferedReader> getFileTable() { return fileTable; }

    public MyHeap<Integer, IValue> getHeap() { return heap; }

    public void setExecutionStack(MyStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public void setSymbolTable(MyDictionary<String, IValue> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setOutputList(MyList<IValue> outputList) {
        this.outputList = outputList;
    }

    public Boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws RuntimeException{
        if(executionStack.isEmpty()){
            System.out.println("Stack is empty");
        }
        IStatement crtStmt = executionStack.pop();
        return crtStmt.execute(this);
    }

    public Integer getId() {
        return freeId++;
    }

    public void setId(int id) {
        this.id = id;
    }
}
