package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    @Test
    void checkEmptyList() {
        MaxMin maxMin = new MaxMin();
        assertThatThrownBy(() -> maxMin.min(new ArrayList<Integer>(), Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkMax() {
        MaxMin maxMin = new MaxMin();
        int max = maxMin.max(List.of(2, 4, 6, 1, 7), Comparator.naturalOrder());
        assertThat(max).isEqualTo(7);
    }

    @Test
    void checkMin() {
        MaxMin maxMin = new MaxMin();
        String min = maxMin.min(List.of("abc", "da", "ac"), Comparator.reverseOrder());
        assertThat(min).isEqualTo("da");
    }
}