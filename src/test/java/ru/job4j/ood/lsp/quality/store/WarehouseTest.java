package ru.job4j.ood.lsp.quality.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.model.Food;

import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenAddFreshFood() {
        Food food = new Food("food1", new GregorianCalendar(2022, 11, 30),
                new GregorianCalendar(2022, 10, 20), 100, 0.1);
        Store warehouse = new Warehouse();
        warehouse.add(food);
        List<Food> foods = warehouse.getAll();
        assertThat(foods.get(0).getName()).isEqualTo("food1");
    }
}