package datastructures.khamutov.lists;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private final static double LOAD_FACTOR = 1.5;


    private T[] list;
    private int size;


    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("list should be bigger then 0");
        } else {
            list = (T[])new Object[capacity];
        }
    }


    public ArrayList() { list = (T[])new Object[DEFAULT_CAPACITY];}

    @Override
    public void add(T value) {
        increaseSizeIfNeeded();
        list[size] = value;
        size++;
    }


    @Override
    public void add(T value, int index) {
        checkBoundForAdd(index);
        increaseSizeIfNeeded();
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;

    }

    @Override
    public T remove(int i) {
        checkBound(i);
        T removedElement = list[i];
        System.arraycopy(list, i + 1, list, i, size - i - 1);
        size--;
        return  removedElement;
    }


    @Override
    public T get(int i) {
        Objects.checkIndex(i, size);
        return  list[i];
    }


    @Override
    public T set(T o, int i) {
        Objects.checkIndex(i, size);
        T value = list[i];
        list[i] = o;
        return  value;
    }

    @Override
    public void clear() {
        size = 0;
        list =(T[]) new Object[DEFAULT_CAPACITY];
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
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int o = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(value)) {
                o = i;
            }
        }
        return o;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(list[i]);
            result.append(",");
        }
        result = result.delete(result.length() - 1, result.length());
        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Object next() {
                if(size==0){
                    throw new IllegalArgumentException("The list is empty");
                }
                Object currentValue = list[counter];
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
        if (list.length == size) {
            T[] tempArray = (T[])new Object[(int) (list.length * LOAD_FACTOR)];
            System.arraycopy(list, 0, tempArray, 0, size);
            list = tempArray;
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
