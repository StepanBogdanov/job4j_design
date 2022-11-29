package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.handler.Checker;
import ru.job4j.ood.lsp.quality.model.Food;

public class Warehouse extends AbstractStore {

    @Override
    public void add(Food food) {
        if (Checker.isForWarehouse(food)) {
            super.add(food);
        }
    }
}
