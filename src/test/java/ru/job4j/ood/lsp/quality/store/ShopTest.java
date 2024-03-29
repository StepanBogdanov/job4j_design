package ru.job4j.ood.lsp.quality.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.handler.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.quality.handler.ExpirationCalculator;
import ru.job4j.ood.lsp.quality.model.Food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    public void whenAddWithoutDiscount() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 10);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10);
        Food food = new Food("foodWithoutDiscount", expiryDate, createDate, 100, 0.1);
        ExpirationCalculator calc = new CalendarExpirationCalculator();
        Store shop = new Shop(calc);
        shop.add(food);
        List<Food> foods = shop.getAll();
        assertThat(foods.get(0).getPrice()).isEqualTo(100);
    }

    @Test
    public void whenAddWithDiscount() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 3);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10);
        Food food = new Food("foodWithDiscount", expiryDate, createDate, 100, 0.1);
        ExpirationCalculator calc = new CalendarExpirationCalculator();
        Store shop = new Shop(calc);
        shop.add(food);
        List<Food> foods = shop.getAll();
        assertThat(foods.get(0).getPrice()).isEqualTo(90);
    }
}