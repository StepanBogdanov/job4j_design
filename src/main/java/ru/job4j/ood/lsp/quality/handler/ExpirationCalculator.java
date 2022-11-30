package ru.job4j.ood.lsp.quality.handler;

public interface ExpirationCalculator<T> {

    double calculateInPercent(T startDate, T endDate);
}
