package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

public interface Parking {

    boolean add(Car car);

    boolean delete(Car car);
}
