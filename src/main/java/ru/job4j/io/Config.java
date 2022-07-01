package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().
                    filter(s -> !s.startsWith("#")).
                    filter(s -> !s.isBlank()).
                    map(s -> s.split("=", 2)).
                    peek(s -> {
                        if (s.length != 2 || s[0].isEmpty() || s[1].isEmpty()) {
                            throw new IllegalArgumentException("Wrong pattern key-value in line: "
                                    + String.join("=", s));
                        }
                    }).
                    forEach(s -> values.put(s[0], s[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }
}
