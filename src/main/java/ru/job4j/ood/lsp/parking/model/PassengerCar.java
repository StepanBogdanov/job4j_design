package ru.job4j.ood.lsp.parking.model;

public class PassengerCar extends Car {

    private int size;

    public PassengerCar(String name) {
        super(name);
        this.size = PASSENGER_CAR_SIZE;
    }
}
