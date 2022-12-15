package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuPrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream prev = System.out;
        PrintStream now = new PrintStream(output);
        System.setOut(now);
        new SimpleMenuPrinter().print(menu);
        System.setOut(prev);
        String expected = String.join(System.lineSeparator(),
                "1.Сходить в магазин",
                "----1.1.Купить продукты",
                "--------1.1.1.Купить хлеб",
                "--------1.1.2.Купить молоко",
                "2.Покормить собаку" + System.lineSeparator());
        assertThat(expected).isEqualTo(output.toString());
    }
}