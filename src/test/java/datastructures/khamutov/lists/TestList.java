package datastructures.khamutov.lists;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestList {
    ArrayList myList = new ArrayList();
    @BeforeEach
    public void init(){
        myList.add("ONE");
        myList.add("TWO");
        myList.add("THREE");
    }

    @Test
    public void testAddAndGet(){
        myList.add("ONE AND HALF",1);
        assertEquals("ONE AND HALF",myList.get(1));

    }

    @Test
    public void testToString(){
        assertEquals("[ONE,TWO,THREE]",myList.toString());
    }

    @Test
    public void testRemove(){
        assertEquals("TWO",myList.remove(1));
    }

    @Test
    public void testSet(){
        assertEquals("TWO",myList.remove(1));
    }

    @Test
    public void testIsEmpty(){
        assertEquals(false,myList.isEmpty());
    }

    @Test
    public void testContains(){
        assertEquals(true,myList.contains("THREE"));
    }


}
