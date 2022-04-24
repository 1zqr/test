package mimic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void should_remove_first_null_element_when_remove_null(){
        //arrange
        List<String> list = new LinkedList<>();
        list.add(null);
        list.add(null);

        //act
        list.remove(null);

        //assert
        assertEquals(1,list.size());
    }

    @Test
    void should_remove_second_null_element_when_remove_null(){
        //arrange
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add(null);

        //act
        list.remove(null);

        //assert
        assertEquals(1,list.size());
    }

    @Test
    void should_not_remove_element_when_remove_null(){
        //arrange
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("1");

        //act
        list.remove(null);

        //assert
        assertEquals(2,list.size());
    }

    @Test
    void should_not_remove_element_when_remove_not_null(){
        //arrange
        List<String> list = new LinkedList<>();
        list.add(null);
        list.add(null);

        //act
        list.remove("1");

        //assert
        assertEquals(2,list.size());
    }

    @Test
    void should_remove_second_element_when_remove_not_null(){
        //arrange
        List<String> list = new LinkedList<>();
        list.add(null);
        list.add("1");

        //act
        list.remove("1");

        //assert
        assertEquals(1,list.size());
    }

    @Test
    void should_remove_first_element_when_remove_not_null(){
        //arrange
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add(null);

        //act
        list.remove("1");

        //assert
        assertEquals(1,list.size());
    }
}