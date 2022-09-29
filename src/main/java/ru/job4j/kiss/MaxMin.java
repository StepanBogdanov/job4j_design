package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, (t, u) -> comparator.compare(t, u) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxMin(value, (t, u) -> comparator.compare(t, u) < 0);
    }

    private <T> T maxMin(List<T> value, BiPredicate<T, T> predicate) {
        if (value.size() == 0) {
            throw new IllegalArgumentException("Value list is empty");
        }
        T rsl = value.get(0);
        for (T t : value) {
            if (predicate.test(t, rsl)) {
                rsl = t;
            }
        }
        return rsl;
    }
}