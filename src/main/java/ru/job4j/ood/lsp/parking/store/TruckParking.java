package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Truck;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;

public class TruckParking implements Store {

    private List<Car> store;

    public TruckParking(int places) {
        this.store = new ArrayList<>(places);
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public boolean delete(Car car) {
        return false;
    }
}
