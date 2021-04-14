package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements HashMap<K, V> {

    private Entry[] container;
    private int capacity;
    private int modCount = 0;
    private int count = 0;

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
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    public int size() {
        return count;
    }


    @Override
    public boolean insert(K key, V value) {
        if ((float) count / capacity >= 0.75) {
            Entry[] newContainer = new Entry[capacity << 1];
            Iterator<Entry> it = iterator();
            while (it.hasNext()) {
                Entry entry = it.next();
                int index = (capacity - 1) & entry.hash;
                if (newContainer[index] == null || newContainer[index].key.equals(key)) {
                    newContainer[index] = entry;
                }
            }
            capacity *= 2;
            container = newContainer;
        }
        Entry entry = new Entry(hash(key), key, value);
        int index = (capacity - 1) & entry.hash;
        if (container[index] == null || container[index].key.equals(key)) {
            container[index] = entry;
            modCount++;
            count++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = (capacity - 1) & hash(key);
        return (V) container[index].value;
    }

    @Override
    public boolean delete(K key) {
        int index = (capacity - 1) & hash(key);
        if (container[index] != null) {
            container[index] = null;
            modCount++;
            count--;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Entry> iterator() {
        return new Iterator<Entry>() {

            private int index = 0;
            private int expectedModCount = modCount;

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
            public Entry next() {
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
        private int hash;
        private K key;
        private V value;

        public Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
