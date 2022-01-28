package datastructures.khamutov.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Iterator <Map.Entry<Integer, String>> it = testMap.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer,String> o = it.next();
            if (o.value.equals("TWO")) {
                it.remove();
            }
        }
        assertEquals(null, testMap.get(2));
    }

    @Test
    public void testPut(){
        Iterator <Map.Entry<Integer, String>> it = testMap.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer,String> o = it.next();
            if (o.value.equals("TWO")) {
                it.remove();
            }
        }
        assertEquals("[ONE,TWO,THREE,FOUR]", testMap.toString());
    }

}
