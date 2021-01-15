package Model.ADTs;

import java.util.HashMap;

public class MyHeap<Integer, IValue> implements IHeap<Integer, IValue>{
    private HashMap<Integer, IValue> heap;
    private Integer nextFreeAddr;

    public MyHeap() {
        heap = new HashMap<Integer, IValue>();
        //nextFreeAddress
    }

    void setNextFreeAddr(Integer i) {
        nextFreeAddr = i;
    }

    @Override
    public HashMap<Integer, IValue> getContent() {
        return heap;
    }

    @Override
    public void setContent(HashMap<Integer, IValue> hp) {
        heap = hp;
    }

    @Override
    public void put(Integer address, IValue content) {
        heap.put(address, content);
    }

    @Override
    public boolean isDefined(Integer address) {
        return heap.containsKey(address);
    }

    @Override
    public IValue lookup(Integer address) {
        return heap.get(address);
    }

    @Override
    public void update(Integer address, IValue content) {
            heap.remove(address, content);
    }

    @Override
    public void delete(Integer address) {
            heap.remove(address);
    }
}
