package ru.job4j.ood.lsp.quality.handler;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.quality.model.Food;
import ru.job4j.ood.lsp.quality.store.Shop;
import ru.job4j.ood.lsp.quality.store.Store;
import ru.job4j.ood.lsp.quality.store.Trash;
import ru.job4j.ood.lsp.quality.store.Warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenAddAllTypeOfFoods() {
        ExpirationCalculator calc = new CalendarExpirationCalculator();
        Store warehouse = new Warehouse(calc);
        Store shop = new Shop(calc);
        Store trash = new Trash(calc);
        List<Store> stores = new ArrayList<>(List.of(warehouse, shop, trash));
        ControlQuality cq = new ControlQuality(stores);

        Calendar expiryDateWH = Calendar.getInstance();
        Calendar createDateWH = Calendar.getInstance();
        expiryDateWH.add(Calendar.DAY_OF_MONTH, 10);
        createDateWH.add(Calendar.DAY_OF_MONTH, -1);
        Food food1 = new Food("warehouse", expiryDateWH, createDateWH, 100, 0.1);

        Calendar expiryDateShop = Calendar.getInstance();
        Calendar createDateShop = Calendar.getInstance();
        expiryDateShop.add(Calendar.DAY_OF_MONTH, 10);
        createDateShop.add(Calendar.DAY_OF_MONTH, -10);
        Food food2 = new Food("shop", expiryDateShop, createDateShop, 100, 0.1);

        Calendar expiryDateTrash = Calendar.getInstance();
        Calendar createDateTrash = Calendar.getInstance();
        expiryDateTrash.add(Calendar.DAY_OF_MONTH, -1);
        createDateTrash.add(Calendar.DAY_OF_MONTH, -10);
        Food food3 = new Food("trash", expiryDateTrash, createDateTrash, 100, 0.1);

        cq.add(food1);
        cq.add(food2);
        cq.add(food3);
        assertThat(warehouse.getAll().get(0)).isEqualTo(food1);
        assertThat(shop.getAll().get(0)).isEqualTo(food2);
        assertThat(trash.getAll().get(0)).isEqualTo(food3);
    }
}