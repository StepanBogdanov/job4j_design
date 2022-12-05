package ru.job4j.ood.lsp.parking.model;

public abstract class Car {

    private String name;
    private int size;

    public int getSize() {
        return size;
    }

    public Car(String name) {
        this.name = name;
    }
}
