package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class StringGeneratorTest {

    @Test
    void whenGenerate() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "name1", "subject", "subject1");
        assertThat(generator.produce(template, args)).isEqualTo("I am a name1, Who are subject1? ");
    }

    @Test
    void whenKeyIsAbsent() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("nam", "name1", "subject", "subject1");
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }

    @Test
    void whenKeyIsRedundant() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "name1", "subject", "subject1", "key", "value");
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }
}