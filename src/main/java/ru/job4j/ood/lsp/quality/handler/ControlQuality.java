package ru.job4j.ood.lsp.quality.handler;

import ru.job4j.ood.lsp.quality.model.Food;
import ru.job4j.ood.lsp.quality.store.Store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void add(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }

    public void resort() {
        List<Food> tempStore = new ArrayList<>();
        for (Store store : stores) {
            for (Food food : store.getAll()) {
                tempStore.add(food);
            }
            store.clear();
        }
        for (Food food : tempStore) {
            add(food);
        }
    }

    public List<Store> getStores() {
        return new ArrayList<>(stores);
    }
}
