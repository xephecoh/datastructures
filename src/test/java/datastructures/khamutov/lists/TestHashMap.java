package datastructures.khamutov.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

public class TestHashMap {
    Map<Integer, String> testMap = new ArrayListMap<>();

    @BeforeEach
    public void init() {
        testMap.put(1, "ONE");
        testMap.put(2, "TWO");
        testMap.put(3, "THREE");
    }

    @Test
    public void testIterator() {
        StringJoiner stringBuilder = new StringJoiner(",");
        Iterator<Map.Entry<Integer, String>> it = testMap.iterator();
        while (it.hasNext()) {
            stringBuilder.add(it.next().value);
        }
        assertEquals("ONE,TWO,THREE", stringBuilder.toString());

    }

    @Test
    public void testIteratorRemove() {
        StringJoiner stringBuilder = new StringJoiner(",");
        Iterator<Map.Entry<Integer, String>> it = testMap.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> next = it.next();
            if (next.value == "TWO") {
                it.remove();
                continue;
            }
            stringBuilder.add(next.value);
        }
        assertEquals("ONE,THREE", stringBuilder.toString());

    }

    @Test
    public void testPutIfAbsent() {
        assertNull(testMap.put(4, "FOUR"));
        StringJoiner joiner = new StringJoiner(",");
        Iterator<Map.Entry<Integer, String>> it = testMap.iterator();
        while (it.hasNext()) {
            joiner.add(it.next().value);
        }
        assertEquals("ONE,TWO,THREE,FOUR", joiner.toString());
    }

    @Test
    public void testPutIfPresent() {
        assertEquals("ONE", testMap.put(1, "FOUR"));
    }

    @Test
    public void testRemove() {
        assertEquals("ONE", testMap.remove(1).value);
        StringJoiner stringBuilder = new StringJoiner(",");
        Iterator<Map.Entry<Integer, String>> it = testMap.iterator();
        while (it.hasNext()) {
            stringBuilder.add(it.next().value);
        }
        assertEquals("TWO,THREE", stringBuilder.toString());

    }

    @Test
    public void testWrongRemove() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> testMap.remove(5));
        assertEquals("No value with key 5", exception.getMessage());
    }

    @Test
    public void testContains() {
        assertTrue(testMap.containsKey(1));
    }

    @Test
    public void testDoesNotContains() {
        assertFalse(testMap.containsKey(8));
    }

    @Test
    public void testSize() {
        assertEquals(3, testMap.size());
        testMap.remove(1);
        assertEquals(2, testMap.size());
    }

    @Test
    public void testContainsIfFalse() {
        assertFalse(testMap.containsKey(6));
    }

}
