package datastructures.khamutov.lists;

import java.util.Iterator;
import java.util.Set;

public interface Map<K,V> extends Iterable {

    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    int size();

    Set<Entry<K, V>> entrySet(K key, V value);


     class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}