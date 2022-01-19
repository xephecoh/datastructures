package datastructures.khamutov.lists;

import java.util.Objects;

public class ArrayListImp implements List {


    private Object[] arraylist;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private final double LOAD_FACTOR = 1.5;



    public ArrayListImp(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("list should be bigger then 0");
        } else {
            arraylist = new Object[capacity];
        }
    }

    public ArrayListImp() {
        arraylist = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(Object o) {
        increaseSizeIfNeeded();
        arraylist[size] =  o;
        size++;
    }


    public void increaseSizeIfNeeded() {
        if (arraylist.length == size) {
            Object[] tempArray =  new Object[(int) (arraylist.length * LOAD_FACTOR)];
            System.arraycopy(arraylist, 0, tempArray, 0, size);
            arraylist = tempArray;
        }
    }


    @Override
    public void add(Object value, int index) {
        Objects.checkIndex(index, size + 1);
        increaseSizeIfNeeded();
        System.arraycopy(arraylist, index, arraylist, index + 1, size - index);
        arraylist[index] = value;
        size++;

    }


    @Override
    public Object remove(int i) {
        Objects.checkIndex(i, size);
        Object removedElement = arraylist[i];
        System.arraycopy(arraylist, i + 1, arraylist, i, size - i - 1);
        size--;
        return removedElement;
    }

    @Override
    public Object get(int i) {
        Objects.checkIndex(i, size);
        return arraylist[i];
    }


    @Override
    public Object set(Object o, int i) {
        Objects.checkIndex(i, size);
        Object t = arraylist[i];
        arraylist[i] =  o;
        return t;
    }

    @Override
    public void clear() {
        size = 0;
        arraylist =  new Object[DEFAULT_CAPACITY];
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

}
