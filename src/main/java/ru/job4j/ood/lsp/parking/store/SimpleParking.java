package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import static ru.job4j.ood.lsp.parking.model.PassengerCar.SIZE;

import java.util.HashSet;
import java.util.Set;

public class SimpleParking implements Parking {

    private int pasCarFreePlaces;
    private int truckFreePlaces;
    private Set<Car> pasCarParking;
    private Set<Car> truckParking;

    public SimpleParking(int pasCarPlaces, int truckPlaces) {
        this.pasCarFreePlaces = pasCarPlaces;
        this.truckFreePlaces = truckPlaces;
        pasCarParking = new HashSet<>(pasCarPlaces);
        truckParking = new HashSet<>(truckPlaces);
    }

    @Override
    public boolean add(Car car) {
        if (isPassengerCar(car) && pasCarFreePlaces >= 1) {
            pasCarParking.add(car);
            pasCarFreePlaces--;
            return true;
        }
        if (!isPassengerCar(car) && truckFreePlaces >= 1) {
            truckParking.add(car);
            truckFreePlaces--;
            return true;
        }
        if (!isPassengerCar(car) && pasCarFreePlaces >= car.getSize()) {
            pasCarParking.add(car);
            pasCarFreePlaces = pasCarFreePlaces - car.getSize();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Car car) {
        if (isPassengerCar(car) && pasCarParking.contains(car)) {
            pasCarParking.remove(car);
            return true;
        }
        if (!isPassengerCar(car) && truckParking.contains(car)) {
            truckParking.remove(car);
            return true;
        }
        if (!isPassengerCar(car) && pasCarParking.contains(car)) {
            pasCarParking.remove(car);
            return true;
        }
        return false;
    }

    private boolean isPassengerCar(Car car) {
        return car.getSize() == SIZE;
    }
}
