package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];

    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = check(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T delValue = check(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        return delValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }

    private T[] grow() {
        return container.length == 0 ? (T[]) new Object[10] : Arrays.copyOf(container, container.length * 2);
    }

    private T check(int index) {
        Objects.checkIndex(index, size);
        T value = container[index];
        modCount++;
        return value;
    }
}
