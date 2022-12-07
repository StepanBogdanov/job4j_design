package ru.job4j.ood.lsp.parking.model;

public abstract class Car {

    private final String name;
    private final int size;

    public Car(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
