package datastructures.khamutov.lists;

import java.util.Iterator;
import java.util.Set;

public interface Map<K,V> {

    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    int size();

    Set<Entry<K, V>> entrySet(K key, V value);

    default Iterator<Entry<K, V>> iterator(){
        return null;
    }
}

    public  class Entry<K, V>{
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}