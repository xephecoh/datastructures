package datastructures.khamutov.lists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public abstract class TestList {
    List myList = getList();
    private static final int ITERATION_NUMBER = 3;

    protected abstract List getList();


    @BeforeEach
    public void init() {
        myList.add("ONE");
        myList.add("TWO");
        myList.add("THREE");
        myList.add("FOUR");
        myList.add("FIVE");
    }

    @Test
    public void testAddAndGet() {
        myList.add("ONE AND HALF", 1);
        assertEquals("ONE AND HALF", myList.get(1));
        List list = myList;

    }

    @Test
    public void testToString() {
        assertEquals("[ONE,TWO,THREE,FOUR,FIVE]", myList.toString());
    }

    @Test
    public void testRemove() {
        assertEquals("TWO", myList.remove(1));
        assertEquals("[ONE,THREE,FOUR,FIVE]",myList.toString());
    }

    @Test
    public void testSet() {
        assertEquals("TWO", myList.remove(1));
    }

    @Test
    public void testIsEmpty() {
        assertFalse(myList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(myList.contains("THREE"));
    }

    @Test
    public void outOfBoundInsertionTest() {
        Exception exception = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myList.add("FOUR", 5)
        );
        String outOfBoundMassage = exception.getMessage();
        assertEquals("Index " + 5 + " is beyond list size", outOfBoundMassage);
    }

    @Test
    public void negativeTest() {
        Exception ex = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myList.add("FIVE", -7)
        );
        String negativeCaseMessage = ex.getMessage();
        assertEquals("Index " + -7 + " is negative", negativeCaseMessage);
    }

    @Test
    public void testGet() {
        assertEquals("ONE", myList.get(0));
    }

    @Test
    public void addToListChangeSize() {
        int size = myList.size();
        for (int i = 0; i < ITERATION_NUMBER; i++) {
            myList.add(null);
        }
        assertEquals(size+ ITERATION_NUMBER, myList.size());
        myList.add("SEVEN");
        assertEquals(9, myList.size());
    }

    @Test
    public void testIteratorForLinkedList() {
        Iterator it = myList.iterator();
        while (it.hasNext()) {
            LinkedList.Node<String> o = (LinkedList.Node<String>) it.next();
            if (o.element.equals("FIVE")) {
                it.remove();
            }
        }
        assertEquals("[ONE,TWO,THREE,FOUR]", myList.toString());
    }

    @Test
    public void testIteratorForArrayList() {
        Iterator it = myList.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o.equals("FIVE")) {
                it.remove();
            }
        }
        assertEquals("[ONE,TWO,THREE,FOUR]", myList.toString());
    }

    @Test
    public void testIteratorIsNext1() {
        Iterator it = myList.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o.equals("FIVE")) {
                it.remove();
            }
        }
        assertEquals("[ONE,TWO,THREE,FOUR]", myList.toString());
    }

}
