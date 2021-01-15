package View;

import Controller.Controller;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState.ProgramState;
import Model.Stmt.*;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repository.Repository;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        IStatement statement1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        IStatement statement2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                                        new CompoundStatement(new AssignmentStatement("a",
                                                new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),new ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                                        new PrintStatement(new VariableExpression("b"))))));

        //ProgramState program = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), statement1);
        //ProgramState program2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), statement1);


        IRepository repository = new Repository();
        //repository.addProgramState(program);
        //repository.addProgramState(program2);

        Controller controller = new Controller(repository);

        int choice;

        while(true){
            System.out.println("Program menu:\n");
            System.out.println("    0. Exit\n");
            System.out.println("    1. Set display flag true\n");
            System.out.println("    2. Set display flag false\n");
            System.out.println("    3. Execute program");
            System.out.println(">>>");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
            switch (choice){
                case 0: return;
                case 1: controller.setDisplayFlag(true);
                case 2: controller.setDisplayFlag(false);
                //case 3: controller.logProgramState();
            }
        }
    }
}
