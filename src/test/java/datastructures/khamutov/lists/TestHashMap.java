package datastructures.khamutov.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestHashMap {
    Map<Integer,String> testMap= new ArrayListMap<>();
    @BeforeEach
    public void init(){
        testMap.put(1,"ONE");
        testMap.put(2,"TWO");
        testMap.put(3,"THREE");
    }

    @Test
    public void testIterator(){
        StringJoiner stringBuilder = new StringJoiner(",");
        Iterator <Map.Entry<Integer, String>> it = testMap.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> next = it.next();
            stringBuilder.add(next.value);
        }
        assertEquals("ONE,TWO,THREE",stringBuilder.toString());

    }

    @Test
    public void testPut(){

        assertEquals(null,  testMap.put(4,"FOUR"));
    }

    @Test
    public void testPutIfPresent(){

        assertEquals("FOUR",  testMap.put(1,"FOUR"));
    }

    @Test
    public void testRemove(){
        assertEquals("ONE",  testMap.remove(1).value);
    }

    @Test
    public void testWrongRemove(){
        Exception exception = assertThrows(NoSuchElementException.class,() -> testMap.remove(5));
        assertEquals("No value with key 5",exception.getMessage());
    }

}
