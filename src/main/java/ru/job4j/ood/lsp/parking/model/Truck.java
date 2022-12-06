package ru.job4j.ood.lsp.parking.model;

public class Truck extends Car {

    private int size;

    public Truck(String name, int size) {
        super(name);
        this.size = size;
        if (size <= PASSENGER_CAR_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
