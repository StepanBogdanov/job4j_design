package ru.job4j.collection;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
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
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> pr = integer -> integer > 2;
        ListUtils.removeIf(input, pr);
        assertThat(Arrays.asList(1, 2), Is.is(input));
    }

    @Test
    public void whenRemoveAllElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> pr = integer -> integer > 0;
        ListUtils.removeIf(input, pr);
        assertTrue(input.isEmpty());
    }

    @Test
    public void whenRemoveNone() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> pr = integer -> integer > 5;
        ListUtils.removeIf(input, pr);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        Predicate<Integer> pr = integer -> integer > 2;
        ListUtils.replaceIf(input, pr, 3);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenReplaceAllElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> pr = integer -> integer > 0;
        ListUtils.replaceIf(input, pr, 5);
        assertThat(Arrays.asList(5, 5, 5), Is.is(input));
    }

    @Test
    public void whenReplaceNone() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> pr = integer -> integer > 5;
        ListUtils.replaceIf(input, pr, 4);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 4), Is.is(input));
    }

    @Test
    public void whenRemoveAllNone() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(5, 6));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveAllAllElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeAll(input, elements);
        assertTrue(input.isEmpty());
    }
}