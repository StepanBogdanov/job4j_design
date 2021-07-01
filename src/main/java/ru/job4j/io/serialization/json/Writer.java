package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Writer {
    private final boolean sex;
    private final int numberOfBooks;
    private final Book[] books;

    public Writer(boolean sex, int numberOfBooks, Book... books) {
        this.sex = sex;
        this.numberOfBooks = numberOfBooks;
        this.books = books;
    }

    public boolean isSex() {
        return sex;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public Book[] getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "sex=" + sex +
                ", numberOfBooks=" + numberOfBooks +
                ", books=" + Arrays.toString(books) +
                '}';
    }
}
