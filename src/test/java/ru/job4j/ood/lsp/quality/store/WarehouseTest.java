package ru.job4j.ood.lsp.quality.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.model.Food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenAddFreshFood() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 10);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 1);
        Food food = new Food("warehouse", expiryDate, createDate, 100, 0.1);
        Store warehouse = new Warehouse();
        warehouse.add(food);
        List<Food> foods = warehouse.getAll();
        assertThat(foods.get(0).getName()).isEqualTo("warehouse");
    }
}