package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty().
                isNotNull().
                isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownType() {
        Box box = new Box(3, 8);
        String name = box.whatsThis();
        assertThat(name).isNotNull().
                isNotEmpty().
                containsIgnoringCase("object").
                isEqualTo("Unknown object");
    }

    @Test
    void isFourVertex() {
        Box box = new Box(4, 4);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isGreaterThan(3).
                isLessThan(5).
                isNotNegative().
                isEqualTo(4);
    }

    @Test
    void isFiveVertex() {
        Box box = new Box(5, 7);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNegative().
                isNotNull().
                isEqualTo(-1);
    }

    @Test
    void isUnknownObject() {
        Box box = new Box(7, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void isKnownObject() {
        Box box = new Box(8, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isThisSphereArea() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isGreaterThan(1000d).
                isLessThan(1500d).
                isCloseTo(1200d, Percentage.withPercentage(10d));


    }

    @Test
    void isUnknownObjectArea() {
        Box box = new Box(1, 10);
        double area = box.getArea();
        assertThat(area).isNotInfinite().
                isNotPositive().
                isEqualTo(0);
    }
}