package ru.job4j.ood.lsp.parking.store;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.PassengerCar;
import ru.job4j.ood.lsp.parking.model.Truck;

import static org.assertj.core.api.Assertions.*;

class SimpleParkingTest {

    @Test
    public void whenAddPassengerCarOnEmptyPlace() {
        Parking parking = new SimpleParking(1, 1);
        Car car = new PassengerCar("PasCar");
        assertThat(parking.add(car)).isTrue();
    }

    @Test
    public void whenAddPassengerCarOnOccupiedPlace() {
        Parking parking = new SimpleParking(1, 1);
        Car car1 = new PassengerCar("PasCar1");
        Car car2 = new PassengerCar("PasCar2");
        parking.add(car1);
        assertThat(parking.add(car2)).isFalse();
    }

    @Test
    public void whenAddTruckOnEmptyPlace() {
        Parking parking = new SimpleParking(1, 1);
        Car car = new Truck("Truck", 2);
        assertThat(parking.add(car)).isTrue();
    }

    @Test
    public void whenAddTruckOnOccupiedPlaceOnTruckParkingAndHaveEmptyPlaceOnPassengerParking() {
        Parking parking = new SimpleParking(2, 1);
        Car car1 = new Truck("Truck1", 2);
        Car car2 = new Truck("Truck2", 2);
        parking.add(car1);
        assertThat(parking.add(car2)).isTrue();
    }

    @Test
    public void whenAddTruckOnOccupiedPlaceOnTruckParkingAndDontHaveEmptyPlaceOnPassengerParking() {
        Parking parking = new SimpleParking(2, 1);
        Car car1 = new PassengerCar("PasCar");
        Car car2 = new Truck("Truck1", 2);
        Car car3 = new Truck("Truck2", 2);
        parking.add(car1);
        parking.add(car2);
        assertThat(parking.add(car3)).isFalse();
    }

    @Test
    public void whenDelPassengerCar() {
        Parking parking = new SimpleParking(1, 0);
        Car car = new PassengerCar("PasCar");
        parking.add(car);
        assertThat(parking.delete(car)).isTrue();
    }

    @Test
    public void whenDelWrongCar() {
        Parking parking = new SimpleParking(1, 0);
        Car car1 = new PassengerCar("PasCar1");
        Car car2 = new PassengerCar("PasCar2");
        parking.add(car1);
        assertThat(parking.delete(car2)).isFalse();
    }

    @Test
    public void whenDelTruckOnTruckParking() {
        Parking parking = new SimpleParking(0, 1);
        Car car = new Truck("Truck", 2);
        parking.add(car);
        assertThat(parking.delete(car)).isTrue();
    }

    @Test
    public void whenDelTruckOnPassengerParking() {
        Parking parking = new SimpleParking(2, 1);
        Car car1 = new Truck("Truck1", 2);
        Car car2 = new Truck("Truck2", 2);
        parking.add(car1);
        parking.add(car2);
        assertThat(parking.delete(car2)).isTrue();
    }
}