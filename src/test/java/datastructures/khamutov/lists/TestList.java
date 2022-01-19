package datastructures.khamutov.lists;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestList {
    ArrayListImp<String> myList = new ArrayListImp<>();
    @BeforeEach
    public void init(){
        myList.add("ONE");
        myList.add("TWO");
        myList.add("THREE");
    }

    @Test
    public void testAdd(){
        myList.add("ONE");
        myList.add("TWO");
        myList.add("THREE");
    }

    @Test
    public void testToString(){
        assertEquals("[ONE,TWO,THREE]",myList.toString());
        myList.clear();
    }

}
