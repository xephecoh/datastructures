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
        this.buckets = (ArrayList<Entry<K, V>>[]) new ArrayList[INITIAL_BUCKET_NUMBER];
    }

    @Override
    public V put(K key, V value) {
        V tempValue;
        Entry<K, V> entry = getOptionalEntry(key);
        int numberOfCurrentBucket = getBucketNumber(key);
        if (buckets[numberOfCurrentBucket] == null) {
            buckets[numberOfCurrentBucket] = new ArrayList<>();
            buckets[numberOfCurrentBucket].add(new Entry<>(key, value));
            size++;
            return null;
        }
        if (entry == null) {
            buckets[numberOfCurrentBucket].add(new Entry<>(key, value));
            size++;
            return null;
        }
        tempValue = entry.value;
        entry.value = value;
        return tempValue;
    }


    private Entry<K, V> getOptionalEntry(K key) {
        List<Entry<K, V>> bucketList = buckets[getBucketNumber(key)];
        if (bucketList == null) {
            return null;
        }
        for (Entry<K, V> entry : bucketList) {
            if (Objects.equals(entry.key, key)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public Entry<K, V> remove(K key) {
        Entry<K, V> entry = getOptionalEntry(key);
        if (entry == null) {
            throw new NoSuchElementException("No value with key " + key.toString());
        }
        int numberOfBuckets = getBucketNumber(key);
        Iterator<Entry<K, V>> iterator = buckets[numberOfBuckets].iterator();
        while (iterator.hasNext()) {
            if (iterator.next().key == key) {
                iterator.remove();
            }
        }
        buckets[numberOfBuckets].add(entry);
        size--;
        return entry;
    }


    private int getBucketNumber(K key) {
        int bucketNumber = Math.abs(key.hashCode() % buckets.length);
        return bucketNumber;
    }

    @Override
    public V get(K key) {
        int bucketNumber = getBucketNumber(key);
        Entry<K, V> optionalEntry = getOptionalEntry(key);
        if (optionalEntry == null) {
            throw new NoSuchElementException("No value with key " + key.toString());
        }
        return optionalEntry.value;
    }


    @Override
    public boolean containsKey(K key) {
        return getOptionalEntry(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<Entry<K, V>> entrySet(K key, V value) {
        return null;
    }


    @Override
    public Iterator iterator() {
        return new Iterator<Entry<K, V>>() {
            int counter = 0;
            int arrayPointer = 0;
            ArrayList<Entry<K, V>> bucket;
            Entry<K, V> next;
            Iterator<Entry<K, V>> iterator;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Entry<K, V> next() {

                while (arrayPointer < INITIAL_BUCKET_NUMBER) {
                    bucket = buckets[arrayPointer];
                    if (iterator == null) {
                        iterator = bucket.iterator();
                    }
                    if (bucket == null) {
                        arrayPointer++;
                        continue;
                    }
                    if (iterator.hasNext()) {
                        next = iterator.next();
                        counter++;
                        return next;
                    } else {
                        arrayPointer++;
                    }
                }
                throw new NoSuchElementException("There are no next element in the map");
            }

            @Override
            public void remove() {
                iterator().remove();
            }
        };
    }
}
