package Model.ADTs;

import Exceptions.MyListException;

public interface IList<T> {
    void addElement(T element);
    T popFirst() throws MyListException;
    boolean isEmpty();
}
