package datastructures.khamutov.lists;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

public class ArrayListMap<K, V> implements Map<K, V> {
    final static int INITIAL_BUCKET_NUMBER = 5;
    private int size;
    ArrayList<Entry<K, V>>[] buckets;


    public ArrayListMap() {
        this.buckets = (ArrayList<Entry<K, V>>[]) new Object[INITIAL_BUCKET_NUMBER];
    }


    public V put(K key, V value) {
        V tempValue = null;
        Entry<K, V> entry = new Entry<>(key, value);
        int numberOfBuckets = getBucketNumber(key);
        for (Entry<K, V> tempEntry : buckets[numberOfBuckets]) {
            if (Objects.equals(tempEntry.key, key)) {
                tempValue = entry.value;
                tempEntry.value = value;
            }
        }
        buckets[numberOfBuckets].add(entry);
        size++;
        return tempValue;
    }

    public V remove(K key, V value) {
        V tempValue = null;
        Entry<K, V> entry = new Entry<>(key, value);
        int numberOfBuckets = getBucketNumber(key);
        for (Entry<K, V> tempEntry : buckets[numberOfBuckets]) {
            if (Objects.equals(tempEntry.key, key)) {
                tempValue = entry.value;
                tempEntry = null;

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
        throw new NoSuchElementException("No element with key " + key);
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
    public Iterator iterator() {
        return new Iterator<Entry<K, V>>() {
            int counter = 0;
            int id = 0;
            int arrayIndex = 0;
            ArrayList<Entry<K, V>> bucket;
            Entry<K, V> next;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Entry<K, V> next() {
                Iterator<Entry<K, V>> iterator;
                while (counter < size) {
                    bucket = buckets[arrayIndex];
                    iterator = bucket.iterator();
                    if (bucket == null) {
                        arrayIndex++;
                        continue;
                    }
                    while (iterator.hasNext()) {
                        next = iterator.next();
                        counter++;
                        id++;
                    }
                    id = 0;
                    return next;
                }
                 throw new NoSuchElementException("there are no next element in the map");
            }
            @Override
            public void remove() {
                iterator().remove();
            }
        };
    }
}
