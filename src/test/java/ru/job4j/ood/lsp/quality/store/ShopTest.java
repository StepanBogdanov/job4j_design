package ru.job4j.ood.lsp.quality.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.model.Food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    public void whenAddWithoutDiscount() {
        Food food = new Food("food2", new GregorianCalendar(2022, 11, 30),
                new GregorianCalendar(2022, 9, 1), 100, 0.1);
        Store shop = new Shop();
        shop.add(food);
        List<Food> foods = shop.getAll();
        assertThat(foods.get(0).getPrice()).isEqualTo(100);
    }

    @Test
    public void whenAddWithDiscount() {
        Food food = new Food("food3", new GregorianCalendar(2022, 11, 30),
                new GregorianCalendar(2022, 0, 1), 100, 0.1);
        Store shop = new Shop();
        shop.add(food);
        List<Food> foods = shop.getAll();
        assertThat(foods.get(0).getPrice()).isEqualTo(90);
    }
}