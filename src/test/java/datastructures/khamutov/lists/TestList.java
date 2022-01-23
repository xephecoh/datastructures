package datastructures.khamutov.lists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public abstract class TestList {
    List<String> testList = getList();
    private static final int ITERATION_NUMBER = 3;

    protected abstract List<String> getList();


    @BeforeEach
    public void init() {
        testList.add("ONE");
        testList.add("TWO");
        testList.add("THREE");
        testList.add("FOUR");
        testList.add("FIVE");
    }

    @Test
    public void testAddAndGet() {
        testList.add("ONE AND HALF", 1);
        assertEquals("ONE AND HALF", testList.get(1));
        assertEquals("[ONE,ONE AND HALF,TWO,THREE,FOUR,FIVE]", testList.toString());
    }


    @Test
    public void testToString() {
        testList.remove(4);
        assertEquals("[ONE,TWO,THREE,FOUR]", testList.toString());
    }

    @Test
    public void testRemove() {
        assertEquals("TWO", testList.remove(1));
        assertEquals("[ONE,THREE,FOUR,FIVE]", testList.toString());
    }

    @Test
    public void testSet() {
        assertEquals("TWO", testList.remove(1));
    }

    @Test
    public void testIsEmpty() {
        assertFalse(testList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(testList.contains("THREE"));
    }

    @Test
    public void outOfBoundInsertionTest() {
        Exception exception = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> testList.add("FOUR", 5)
        );
        String outOfBoundMassage = exception.getMessage();
        assertEquals("Index " + 5 + " is beyond list size", outOfBoundMassage);
    }

    @Test
    public void negativeTest() {
        Exception ex = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> testList.add("FIVE", -7)
        );
        String negativeCaseMessage = ex.getMessage();
        assertEquals("Index " + -7 + " is negative", negativeCaseMessage);
    }

    @Test
    public void testGet() {
        assertEquals("FIVE", testList.get(4));
    }

    @Test
    public void addToListChangeSize() {
        int size = testList.size();
        for (int i = 0; i < ITERATION_NUMBER; i++) {
            testList.add(null);
        }
        assertEquals(size + ITERATION_NUMBER, testList.size());
        testList.add("SEVEN");
        assertEquals(9, testList.size());
    }

    @Test
    public void testIteratorRemoveForLinkedList() {
        Iterator it = testList.iterator();
        while (it.hasNext()) {
            LinkedList.Node<String> o = (LinkedList.Node<String>) it.next();
            if (o.element.equals("FIVE")) {
                it.remove();
            }
        }
        assertEquals("[ONE,TWO,THREE,FOUR]", testList.toString());
    }

    @Test
    public void testIteratorForArrayList() {
        Iterator it = testList.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o.equals("FIVE")) {
                it.remove();
            }
        }
        assertEquals("[ONE,TWO,THREE,FOUR]", testList.toString());
    }


}
