package datastructures.khamutov.lists;

import java.util.Objects;

public class LinkedList<T> implements List {
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
    public void add(Object value) {
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
    public void add(Object value, int index) {
        Objects.checkIndex(index, size + 1);
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
    public Object remove(int index) {
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
    public Object get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = getNodeByIndex(index);
        return currentNode.element;
    }

    @Override
    public Object set(Object element, int index) {
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
}
