package ru.job4j.ood.lsp.quality.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.handler.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.quality.handler.ExpirationCalculator;
import ru.job4j.ood.lsp.quality.model.Food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    public void whenAddTrash() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) - 1);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10);
        Food food = new Food("trash", expiryDate, createDate, 100, 0.1);
        ExpirationCalculator calc = new CalendarExpirationCalculator();
        Store trash = new Trash(calc);
        trash.add(food);
        List<Food> foods = trash.getAll();
        assertThat(foods.get(0).getName()).isEqualTo("trash");
    }

}