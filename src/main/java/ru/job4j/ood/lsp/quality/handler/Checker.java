package ru.job4j.ood.lsp.quality.handler;

import ru.job4j.ood.lsp.quality.model.Food;

import java.util.GregorianCalendar;

public class Checker {

    private static final int STORAGE_LIMIT = 25;
    private static final int DISCOUNT_LIMIT = 75;

    public static boolean isForWarehouse(Food food) {
        long expiry = food.getExpiryDate().getTimeInMillis();
        long now = new GregorianCalendar().getTimeInMillis();
        long create = food.getCreateDate().getTimeInMillis();
        return ((now - create) * 100.0) / (expiry - create) < STORAGE_LIMIT;
    }

    public static boolean isTrash(Food food) {
        long expiry = food.getExpiryDate().getTimeInMillis();
        long now = new GregorianCalendar().getTimeInMillis();
        return now > expiry;
    }

    public static boolean isDiscount(Food food) {
        long expiry = food.getExpiryDate().getTimeInMillis();
        long now = new GregorianCalendar().getTimeInMillis();
        long create = food.getCreateDate().getTimeInMillis();
        return ((now - create) * 100.0) / (expiry - create) > DISCOUNT_LIMIT;
    }
}
