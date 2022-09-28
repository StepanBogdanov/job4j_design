package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenParseEmptyArray() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(new String[0]))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void whenParseNameWithoutSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"1Name"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1Name")
                .hasMessageContaining("symbol");

    }

    @Test
    void whenParseWithoutKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"1=Name", "=Name"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=Name")
                .hasMessageContaining("key");
    }

    @Test
    void whenParseWithoutValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"1=Name", "2="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2=")
                .hasMessageContaining("value");
    }
}