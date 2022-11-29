package ru.job4j.ood.lsp.quality.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.model.Food;

import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    public void whenAddTrash() {
        Food food = new Food("food4", new GregorianCalendar(2022, 9, 30),
                new GregorianCalendar(2022, 0, 1), 100, 0.1);
        Store trash = new Trash();
        trash.add(food);
        List<Food> foods = trash.getAll();
        assertThat(foods.get(0).getName()).isEqualTo("food4");
    }

}