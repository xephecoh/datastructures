package datastructures.khamutov.lists;


import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class ArrayListMap<K, V> implements Map<K, V> {
    final static int INITIAL_BUCKET_NUMBER = 5;
    private int size;
    ArrayList<Entry<K,V>>[] buckets;


    public ArrayListMap() {
        this.buckets =(ArrayList<Entry<K,V>>[]) new Object[INITIAL_BUCKET_NUMBER];
    }


    public V put(K key, V value) {
        V tempValue = null;
        Entry<K, V> entry = new Entry<>(key, value);
        int numberOfBuckets = getBucketNumber(key);
        for ( Entry<K, V> tempEntry : buckets[numberOfBuckets]) {
            if (Objects.equals(tempEntry.key, key)) {
                tempValue = entry.value;
                tempEntry.value = value;
            }
        }
        buckets[numberOfBuckets].add(entry);
        size++;
        return tempValue;
    }


    private int getBucketNumber(K key) {
        int bucketNumber = key.hashCode() % buckets.length;
        return bucketNumber;
    }

    @Override
    public V get(K key) {
        int bucketNumber = getBucketNumber(key);
        for (Entry<K, V> entry : buckets[bucketNumber]) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<Entry<K, V>> entrySet(K key, V value) {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }
}