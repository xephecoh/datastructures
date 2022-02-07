package datastructures.khamutov.map;

public interface Map<K, V> extends Iterable<Map.Entry<K,V>> {

    V put(K key, V value);

    V get(K key);

    Entry<K, V> remove(K key);

    boolean containsKey(K key);

    int size();


    class Entry<K, V> {
         public K key;
         public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}