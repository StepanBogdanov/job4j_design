package ru.job4j.ood.lsp.parking.model;

public abstract class Car {

    public static final int PASSENGER_CAR_SIZE = 1;

    private String name;
    private int size;

    public Car(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }
}
