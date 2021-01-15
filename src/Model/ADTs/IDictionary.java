package Model.ADTs;

import java.util.HashMap;

public interface IDictionary<T, V> {
    void put(T key, V value);
    boolean isDefined(T id);
    V lookup(T id);
    void update(T id, V value);
    void delete(T id);
    HashMap<T, V> getContent();
}
