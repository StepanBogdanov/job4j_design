package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (delete(id)) {
            add(model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T found = findById(id);
        if (found != null) {
            mem.remove(found);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T found : mem) {
            if (found.getId().equals(id)) {
                return found;
            }
        }
        return null;
    }
}

