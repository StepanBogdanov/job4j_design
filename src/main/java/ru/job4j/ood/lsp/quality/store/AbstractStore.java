package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private List<Food> store = new ArrayList<>();

    public boolean add(Food food) {
        boolean rsl = false;
        if (isNotExpired(food)) {
            store.add(food);
            rsl = true;
        }
        return rsl;
    }

    public List<Food> getAll() {
        return new ArrayList<Food>(store);
    }

    protected abstract boolean isNotExpired(Food food);
}
