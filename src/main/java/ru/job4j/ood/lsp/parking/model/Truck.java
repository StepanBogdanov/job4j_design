package ru.job4j.ood.lsp.parking.model;

import static ru.job4j.ood.lsp.parking.model.PassengerCar.SIZE;

public class Truck extends Car {

    public Truck(String name, int size) {
        super(name, size);
        if (size <= SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
