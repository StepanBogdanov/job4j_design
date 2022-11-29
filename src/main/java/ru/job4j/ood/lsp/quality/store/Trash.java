package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.handler.Checker;
import ru.job4j.ood.lsp.quality.model.Food;

public class Trash extends AbstractStore {

    @Override
    public void add(Food food) {
        if (Checker.isTrash(food)) {
            super.add(food);
        }
    }
}
