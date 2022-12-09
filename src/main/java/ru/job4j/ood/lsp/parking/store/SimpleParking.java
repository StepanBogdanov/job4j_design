package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import static ru.job4j.ood.lsp.parking.model.PassengerCar.SIZE;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {

    private int pasCarFreePlaces;
    private int truckFreePlaces;
    private List<Car> pasCarParking;
    private List<Car> truckParking;

    public SimpleParking(int pasCarPlaces, int truckPlaces) {
        this.pasCarFreePlaces = pasCarPlaces;
        this.truckFreePlaces = truckPlaces;
        pasCarParking = new ArrayList<>(pasCarPlaces);
        truckParking = new ArrayList<>(truckPlaces);
    }

    @Override
    public boolean add(Car car) {
        boolean rsl = false;
        if (isPassengerCar(car)) {
            if (pasCarFreePlaces > 0) {
                pasCarParking.add(car);
                pasCarFreePlaces--;
                rsl = true;
            }
        } else {
            if (truckFreePlaces > 0) {
                truckParking.add(car);
                truckFreePlaces--;
                rsl = true;
            } else if (pasCarFreePlaces >= car.getSize()) {
                pasCarParking.add(car);
                pasCarFreePlaces = pasCarFreePlaces - car.getSize();
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public boolean delete(Car car) {
        boolean rsl = false;
        if (isPassengerCar(car)) {
            for (Car pasCar : pasCarParking) {
                if (pasCar.equals(car)) {
                    pasCarParking.remove(car);
                    rsl = true;
                    break;
                }
            }
        } else {
            for (Car truck : truckParking) {
                if (truck.equals(car)) {
                    truckParking.remove(car);
                    rsl = true;
                    break;
                }
            }
            if (!rsl) {
                for (Car truck : pasCarParking) {
                    if (truck.equals(car)) {
                        pasCarParking.remove(car);
                        rsl = true;
                        break;
                    }
                }
            }
        }
        return rsl;
    }

    private boolean isPassengerCar(Car car) {
        return car.getSize() == SIZE;
    }
}
