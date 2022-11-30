package ru.job4j.ood.lsp.quality.store;

import ru.job4j.ood.lsp.quality.handler.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.quality.handler.ExpirationCalculator;
import ru.job4j.ood.lsp.quality.model.Food;

public class Shop extends AbstractStore {

    private ExpirationCalculator calc;
    private static final double STORAGE_LIMIT = 25;
    private static final double DISCOUNT_LIMIT = 75;
    private static final double SALE_LIMIT = 100;

    public Shop() {
        this.calc = new CalendarExpirationCalculator();
    }

    @Override
    protected boolean isNotExpired(Food food) {
        boolean rsl = false;
        double expirationPercent = calc.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        if (expirationPercent > STORAGE_LIMIT && expirationPercent < SALE_LIMIT) {
            if (expirationPercent > DISCOUNT_LIMIT) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount()));
            }
            rsl = true;
        }
        return rsl;
    }

}
