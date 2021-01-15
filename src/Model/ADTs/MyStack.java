package Model.ADTs;

import java.util.Stack;

public class
MyStack<IStatement> implements IStack<IStatement> {
    private Stack<IStatement> stack;

    public MyStack(){
        stack = new Stack<IStatement>();
    }

    @Override
    public IStatement pop() {
        return stack.pop();
    }

    @Override
    public void push(IStatement statement) {
        stack.push(statement);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
