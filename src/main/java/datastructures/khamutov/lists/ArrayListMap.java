package datastructures.khamutov.lists;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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


    @Override
    public Entry<K, V> remove(K key) {
        int numberOfBucket = getBucketNumber(key);
        ArrayList<Entry<K, V>> bucketWithElement = buckets[numberOfBucket];
        if(bucketWithElement == null){
            throw new NoSuchElementException("No value with key " + key.toString());
        }
        Entry<K, V> entry = getOptionalEntry(key);
        if (entry == null) {
            throw new NoSuchElementException("No value with key " + key.toString());
        }
        Iterator<Entry<K, V>> iterator = bucketWithElement.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().key == key) {
                iterator.remove();
            }
        }
        size--;
        return entry;
    }

    @Override
    public V get(K key) {
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
    public Iterator iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            int counter = 0;
            int arrayPointer = 0;
            ArrayList<Entry<K, V>> bucket;
            Entry<K, V> next;
            Iterator<Entry<K, V>> listIterator;


            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Entry<K, V> next() {

                while (arrayPointer < INITIAL_BUCKET_NUMBER) {
                    bucket = buckets[arrayPointer];
                    if (bucket == null) {
                        arrayPointer++;
                        continue;
                    }
                    if (listIterator == null) {
                        listIterator = bucket.iterator();
                    }
                    if (listIterator.hasNext()) {
                        next = listIterator.next();
                        counter++;
                        return next;
                    } else {
                        arrayPointer++;
                        listIterator = null;
                    }
                }
                throw new NoSuchElementException("There are no next element in the map");
            }

            @Override
            public void remove() {
                listIterator.remove();
            }
        };
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
    private int getBucketNumber(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
}
