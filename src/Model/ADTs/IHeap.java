package Model.ADTs;

import java.util.HashMap;

public interface IHeap<Integer, IValue> {
    HashMap<Integer, IValue> getContent();
    void setContent(HashMap<Integer, IValue> hp);
    void put(Integer address, IValue content);
    boolean isDefined(Integer address);
    IValue lookup(Integer address);
    void update(Integer address, IValue content);
    void delete(Integer address );
}
