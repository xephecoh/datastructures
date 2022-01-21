package datastructures.khamutov.lists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public abstract class TestList {
    List myList = getList();

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

    }

    @Test
    public void testToString() {
        assertEquals("[ONE,TWO,THREE]", myList.toString());
    }

    @Test
    public void testRemove() {
        assertEquals("TWO", myList.remove(1));
    }

    @Test
    public void testSet() {
        assertEquals("TWO", myList.remove(1));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(false, myList.isEmpty());
    }

    @Test
    public void testContains() {
        assertEquals(true, myList.contains("THREE"));
    }

    @Test
    public void outOfBoundInsertionTest() {
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myList.add("FOUR", 5)
        );
        Exception ex = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> myList.add("FIVE", -7)
        );

        String currentMessage = ex.getMessage();
        assertEquals("Index is negative",currentMessage);
    }

    @Test
    public void testGet(){
        assertEquals("ONE",myList.get(0));
    }
    @Test
    public void addToListChangeSize() {
        for (int i = myList.size(); i < 3; i++) {
            myList.add(null);
        }
        assertEquals(3, myList.size());
        myList.add("SEVEN");
        assertEquals(4, myList.size());
    }
    @Test
    public void testIterator(){
        Iterator it = myList.iterator();
        while(it.hasNext()){
            Object o = it.next();
            if(o.equals("FIVE")){
                it.remove();
            }
        }

        assertEquals("[ONE,TWO,THREE,FOUR]",myList.toString());

    }




}
