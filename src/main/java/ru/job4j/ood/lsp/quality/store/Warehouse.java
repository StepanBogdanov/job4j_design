package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.handler.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.quality.handler.ExpirationCalculator;
import ru.job4j.ood.lsp.quality.model.Food;

public class Warehouse extends AbstractStore {

    private static final double STORAGE_LIMIT = 25;

    private ExpirationCalculator calc;

    public Warehouse(ExpirationCalculator calc) {
        this.calc = calc;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        double expirationPercent = calc.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return expirationPercent < STORAGE_LIMIT;
    }

}
