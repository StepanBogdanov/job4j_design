package ru.job4j.ood.lsp.quality.handler;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.model.Food;
import ru.job4j.ood.lsp.quality.store.Shop;
import ru.job4j.ood.lsp.quality.store.Store;
import ru.job4j.ood.lsp.quality.store.Trash;
import ru.job4j.ood.lsp.quality.store.Warehouse;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenAddAllTypeOfFoods() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = new ArrayList<>(List.of(warehouse, shop, trash));
        ControlQuality cq = new ControlQuality(stores);
        Food food1 = new Food("food1", new GregorianCalendar(2022, 11, 30),
                new GregorianCalendar(2022, 10, 20), 100, 0.1);
        Food food2 = new Food("food2", new GregorianCalendar(2022, 11, 30),
                new GregorianCalendar(2022, 9, 1), 100, 0.1);
        Food food3 = new Food("food3", new GregorianCalendar(2022, 9, 30),
                new GregorianCalendar(2022, 0, 1), 100, 0.1);
        cq.add(food1);
        cq.add(food2);
        cq.add(food3);
        assertThat(cq.getStores().get(0).getAll().get(0)).isEqualTo(food1);
        assertThat(cq.getStores().get(1).getAll().get(0)).isEqualTo(food2);
        assertThat(cq.getStores().get(2).getAll().get(0)).isEqualTo(food3);
    }
}