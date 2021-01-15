package Model.ADTs;

import Exceptions.MyListException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements IList<T>{
    private List<T> list;

    public MyList(){
        list = new ArrayList<T>();
    }

    public void addElement(T element){
        list.add(element);
    }

    @Override
    public T popFirst() throws MyListException{
        if(list.size() == 0)
            throw new MyListException("Empty list");
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
