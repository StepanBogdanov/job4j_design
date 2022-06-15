package ru.job4j.collection.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.collection.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    SimpleMap<Integer, String> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
    }

    @Test
    public void whenGet() {
        Assert.assertEquals("one", map.get(1));
    }

    @Test
    public void whenGetWrongKey() {
        Assert.assertEquals(null, map.get(4));
    }

    @Test
    public void whenPutSameKeysAndGet() {
        map.put(1, "ONE");
        Assert.assertEquals("one", map.get(1));
    }

    @Test
    public void whenRemove() {
        Assert.assertTrue(map.remove(2));
        Assert.assertEquals(null, map.get(2));
    }

    @Test
    public void whenRemoveWrongKey() {
        Assert.assertFalse(map.remove(4));
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        map = new SimpleMap<>();
        Assert.assertFalse(map.iterator().hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = map.iterator();
        map.put(4, "four");
        iterator.next();
    }
}