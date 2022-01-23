package datastructures.khamutov.lists;

import java.util.Iterator;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private final static double LOAD_FACTOR = 1.5;


    private Object[] arraylist;
    private int size;


    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("list should be bigger then 0");
        } else {
            arraylist = new Object[capacity];
        }
    }

    public ArrayList() {
        arraylist = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        increaseSizeIfNeeded();
        arraylist[size] = value;
        size++;
    }


    @Override
    public void add(T value, int index) {
        checkBoundForAdd(index);
        increaseSizeIfNeeded();
        System.arraycopy(arraylist, index, arraylist, index + 1, size - index);
        arraylist[index] = value;
        size++;

    }

    @Override
    public T remove(int i) {
        checkBound(i);
        Object removedElement =arraylist[i];
        System.arraycopy(arraylist, i + 1, arraylist, i, size - i - 1);
        size--;
        return (T) removedElement;
    }


    @Override
    public T get(int i) {
        Objects.checkIndex(i, size);
        return (T) arraylist[i];
    }


    @Override
    public T set(Object o, int i) {
        Objects.checkIndex(i, size);
        Object value = arraylist[i];
        arraylist[i] = o;
        return (T) value;
    }

    @Override
    public void clear() {
        size = 0;
        arraylist = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < arraylist.length; i++) {
            if (arraylist[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int o = -1;
        for (int i = 0; i < arraylist.length; i++) {
            if (arraylist[i].equals(value)) {
                o = i;
            }
        }
        return o;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(arraylist[i]);
            result.append(",");
        }
        result = result.delete(result.length() - 1, result.length());
        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Object next() {
                Object currentValue = arraylist[counter];
                counter++;
                return currentValue;
            }

            @Override
            public void remove() {
                ArrayList.this.remove(counter - 1);
            }
        };
    }

    private void increaseSizeIfNeeded() {
        if (arraylist.length == size) {
            Object[] tempArray = new Object[(int) (arraylist.length * LOAD_FACTOR)];
            System.arraycopy(arraylist, 0, tempArray, 0, size);
            arraylist = tempArray;
        }
    }

    private void checkBound(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is beyond list size");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index is negative");
        }
    }

    private void checkBoundForAdd(int index) {
        if (index >= size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is beyond list size");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is negative");
        }
    }
}
