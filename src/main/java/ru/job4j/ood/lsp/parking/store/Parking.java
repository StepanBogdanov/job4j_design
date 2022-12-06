package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

import java.util.ArrayList;
import java.util.List;

public class Parking implements Store {

    private int pasCarFreePlaces;
    private int truckFreePlaces;
    private List<Car> pasCarParking;
    private List<Car> truckParking;

    public Parking(int pasCarPlaces, int truckPlaces) {
        this.pasCarFreePlaces = pasCarPlaces;
        this.truckFreePlaces = truckPlaces;
        pasCarParking = new ArrayList<>(pasCarPlaces);
        truckParking = new ArrayList<>(truckPlaces);
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public boolean delete(Car car) {
        return false;
    }

    private boolean isPassengerCar(Car car) {
        return car.getSize() == Car.PASSENGER_CAR_SIZE;
    }
}
