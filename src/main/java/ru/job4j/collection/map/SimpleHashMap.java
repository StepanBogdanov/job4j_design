package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements HashMap<K, V> {

    private Entry<K, V>[] container;
    private int capacity;
    private int modCount = 0;
    private int count = 0;
    private final float loadFactor = 0.75f;

    public SimpleHashMap() {
        capacity = 16;
        container =  new Entry[capacity];
    }

    public SimpleHashMap(int capacity) {
        this.capacity = capacity;
        container =  new Entry[capacity];
    }

    static int hash(Object key) {
        int h = key.hashCode();
        return h ^ h >>> 16;
    }

    public int size() {
        return count;
    }

    private void expand() {
        Entry<K, V>[] newContainer = new Entry[capacity << 1];
        for (Entry<K, V> entry : (Iterable<Entry<K, V>>) this) {
            int index = (capacity - 1) & entry.hash;
            if (newContainer[index] == null || newContainer[index].key.equals(entry.key)) {
                newContainer[index] = entry;
            }
        }
        capacity *= 2;
        container = newContainer;
    }

    @Override
    public boolean insert(K key, V value) {
        if ((float) count / capacity >= loadFactor) {
            expand();
        }
        Entry<K, V> entry = new Entry<>(hash(key), key, value);
        int index = (capacity - 1) & entry.hash;
        if (container[index] == null) {
            container[index] = entry;
            modCount++;
            count++;
            return true;
        }
        if (container[index].key.equals(key)) {
            container[index] = entry;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = (capacity - 1) & hash(key);
        if (key.equals(container[index].key)) {
            return container[index].value;
        }
        return null;
    }

    @Override
    public boolean delete(K key) {
        int index = (capacity - 1) & hash(key);
        if (container[index] != null && key.equals(container[index].key)) {
            container[index] = null;
            modCount++;
            count--;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {

            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                for ( ; index < capacity; index++) {
                    if (container[index] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Entry<K, V> next() {
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

    public class Entry<K, V> {
        private final int hash;
        private final K key;
        private final V value;

        public Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }
    }
}
