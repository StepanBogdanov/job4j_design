package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.model.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private List<Food> store = new ArrayList<>();

    public void add(Food food) {
        store.add(food);
    }

    public List<Food> getAll() {
        return new ArrayList<Food>(store);
    }
}
