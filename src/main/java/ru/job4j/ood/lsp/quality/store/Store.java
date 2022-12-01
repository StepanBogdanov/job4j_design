package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.model.Food;

import java.util.List;

public interface Store {

    boolean add(Food food);

    List<Food> getAll();
}
