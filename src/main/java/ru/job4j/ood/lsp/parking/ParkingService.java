package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.store.PassengerCarParking;
import ru.job4j.ood.lsp.parking.store.Store;
import ru.job4j.ood.lsp.parking.store.TruckParking;

public class ParkingService {

    private Store passengerCarParking;
    private Store truckParking;

    public ParkingService(int passengerCarPlaces, int truckPlaces) {
        this.passengerCarParking = new PassengerCarParking(passengerCarPlaces);
        this.truckParking = new TruckParking(truckPlaces);
    }

    public void park(Car car) {
        if (isPassengerCar(car)) {
            passengerCarParking.add(car);
        } else {
            truckParking.add(car);
        }
    }

    public void leave(Car car) {
        if (isPassengerCar(car)) {
            passengerCarParking.delete(car);
        } else {
            truckParking.delete(car);
        }
    }

    private boolean isPassengerCar(Car car) {
        return car.getSize() == 1;
    }

}
