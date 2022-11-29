package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.handler.Checker;
import ru.job4j.ood.lsp.quality.model.Food;

public class Shop extends AbstractStore {

    @Override
    public void add(Food food) {
        if (!Checker.isTrash(food) && !Checker.isForWarehouse(food)) {
            if (Checker.isDiscount(food)) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount()));
            }
            super.add(food);
        }

    }
}
