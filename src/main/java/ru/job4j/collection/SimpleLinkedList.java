package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> first = null;
    private Node<E> last = null;
    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = first.next;
        }
        return node.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int expectedModCount = modCount;
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private class Node<E> {

        private Node<E> prev;
        private Node<E> next;
        private E value;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
}