package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.handler.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.quality.handler.ExpirationCalculator;
import ru.job4j.ood.lsp.quality.model.Food;

public class Trash extends AbstractStore {

    private static final double SALE_LIMIT = 100;

    private ExpirationCalculator calc;

    public Trash(ExpirationCalculator calc) {
        this.calc = calc;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        double expirationPercent = calc.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return expirationPercent > SALE_LIMIT;
    }

}
