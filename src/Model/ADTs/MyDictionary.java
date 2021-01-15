package Model.ADTs;

import java.util.HashMap;

public class MyDictionary<T, V> implements IDictionary<T, V>{
    private HashMap<T, V> dictionary;

    public MyDictionary(){
        dictionary = new HashMap<T, V>();
    }

    @Override
    public void put(T key, V value) { dictionary.put(key,value); }

    @Override
    public boolean isDefined(T id) {
        return dictionary.containsKey(id);
    }

    @Override
    public V lookup(T id) {
        return dictionary.get(id);
    }

    @Override
    public void update(T id, V value) {
        dictionary.replace(id, value);
    }

    @Override
    public void delete(T id) {
        dictionary.remove(id);
    }

    @Override
    public HashMap<T, V> getContent() {
        return dictionary;
    }
}
