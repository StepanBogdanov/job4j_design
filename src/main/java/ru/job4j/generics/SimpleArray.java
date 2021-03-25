package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int length = 0;

    public SimpleArray(int size) {
        array = (T[]) new Object[size];
    }

    public int getLength() {
        return length;
    }

    public void add(T model) {
        array[length] = model;
        length++;
    }


    public void set(int index, T model) {
        Objects.checkIndex(index, length);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, length);
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        length--;
    }

    public T get(int index) {
        Objects.checkIndex(index, length);
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
        return it;
    }
}
