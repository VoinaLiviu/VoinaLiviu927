package View;

import Controller.Controller;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState.ProgramState;
import Model.Stmt.*;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String args[]){
        IStatement statement1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        MyStack<IStatement> executionStack = new MyStack<>();
        MyDictionary<String, IValue> symbolTable = new MyDictionary<>();
        MyList<IValue> outputList = new MyList<>();
        MyDictionary<String, BufferedReader> fileTable = new MyDictionary<>();

        ProgramState state1 = new ProgramState(executionStack, symbolTable, outputList, statement1, fileTable);

        Repository repo1 = new Repository();
        repo1.addProgramState(state1);
        repo1.setLogFilePath("D:\\Liviu\\Faculta\\map2\\ToyLanguageInterpretor\\files\\prgStateLog.txt");

        Controller ctrl1 = new Controller(repo1);

        TextMenu textMenu = new TextMenu();
        textMenu.addCommand(new ExitCommand("0","exit"));
        textMenu.addCommand(new RunExample("1", state1.toString(),ctrl1));
        textMenu.show();
    }
}
