package ru.job4j.collection.map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMapTest {

    @Test
    public void whenInsertThenGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        Integer rsl = map.get("first");
        assertThat(rsl, is(1));
    }

    @Test
    public void whenInsertThenIt() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        Integer rsl = map.iterator().next().getValue();
        assertThat(rsl, is(1));
    }

    @Test(expected = NullPointerException.class)
    public void whenWrongKey() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.get("first");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.iterator().next();
    }

//    @Test(expected = ConcurrentModificationException.class)
//    public void whenCorruptedIt() {
//        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
//        map.insert("first", 1);
//        Iterator it = map.iterator();
//        map.insert("second", 2);
//        it.next();
//    }

    @Test
    public void whenExpandMap() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(2);
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        assertThat(map.size(), is(3));
    }
}