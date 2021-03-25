package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

public class SimpleArrayTest {

    private SimpleArray<Integer> simpleArray;

    @Before
    public void setUp() {
        simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
    }

    @Test
    public void whenAddElement() {
        simpleArray.add(4);
        assertThat(simpleArray.get(3), is(4));
    }

    @Test
    public void whenSetElement() {
        simpleArray.set(1, 5);
        assertThat(simpleArray.get(1), is(5));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenIncorrectIndex() {
        simpleArray.get(9);
    }

    @Test
    public void whenRemoveElement() {
        simpleArray.remove(1);
        assertThat(simpleArray.get(1), is(3));
    }

}