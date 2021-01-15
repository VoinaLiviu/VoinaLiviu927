package Model.ADTs;

public interface IStack<IStatement> {
    IStatement pop();
    void push(IStatement statement);
    boolean isEmpty();
}
