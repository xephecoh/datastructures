package datastructures.khamutov.lists;


import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;


    @Override
    public void add(T value) {
        Node<T> newNode = new Node(value);
        if (first == null) {
            first = this.last = newNode;
        } else {
            this.last.next = newNode;
            last = newNode;
        }
        size++;

    }

    @Override
    public void add(T value, int index) {
        checkBoundForAdd(index);
        Node<T> newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
        } else if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else if (index == size) {
            last.next = newNode;
            last = newNode;
        } else {
            Node<T> currentNode = getNodeByIndex(index - 1);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
        size++;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement;
        if (index == 0) {
            removedElement = first.element;
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            Node<T> previous = getNodeByIndex(index - 1);
            removedElement = previous.next.element;
            previous.next = previous.next.next;
            if (index == size - 1) {
                last = previous;
            }
        }
        size--;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = getNodeByIndex(index);
        return currentNode.element;
    }

    @Override
    public T set(Object element, int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = getNodeByIndex(index);
        currentNode.element = (T) element;
        return currentNode.element;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object value) {
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int counter = -1;
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(value)) {
                counter = i;
            }
            current = current.next;
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> currentNode = first;
        for (int i = 0; i < size - 1; i++) {
            result.append(currentNode.element);
            result.append(",");
            currentNode = currentNode.next;
        }
         result.append(last.element);
        //result = result.delete(result.length() - 1, result.length());
       // result = result.delete(result.length() - 1, result.length());
        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {

            int counter = 0;
            Node<T> currentValue = first;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Node<T> next() {
                Node<T> tempValue = currentValue;
                counter++;
                currentValue = currentValue.next;
                return tempValue;
            }

            @Override
            public void remove() {
                LinkedList.this.remove(counter-1);
            }
        };
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
