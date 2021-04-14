package ru.job4j.collection.map;

public interface HashMap<K, V> extends Iterable {
    boolean insert(K key, V value);
    V get(K key);
    boolean delete(K key);
}
