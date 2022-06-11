package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, Is.is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemoveOneElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, i -> i == 3);
        assertThat(input, Is.is(Arrays.asList(1, 2, 4)));
    }

    @Test
    public void whenRemoveTwoElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, i -> i >= 3);
        assertThat(input, Is.is(Arrays.asList(1, 2)));
    }

    @Test
    public void whenReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.replaceIf(input, i -> i == 3, 5);
        assertThat(input, Is.is(Arrays.asList(1, 2, 5, 4)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> input2 = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(input, input2);
        assertThat(input, Is.is(Arrays.asList(2, 4)));
    }

}