package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private T[] container;
    private int capacity;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        capacity = 10;
        container = (T[]) new Object[capacity];
    }

    public SimpleArray(int capacity) {
        this.capacity = capacity;
        container = (T[]) new Object[capacity];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    public void add(T model) {
        if (size == capacity) {
            int newCapacity = capacity + capacity / 2 + 1;
            T[] newContainer = (T[]) new Object[newCapacity];
            System.arraycopy(container, 0, newContainer, 0, size);
            container = newContainer;
            capacity = newCapacity;
        }
        container[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
