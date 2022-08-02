package ru.job4j.search;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Wrong key");
        }
        return values.get(key);
    }

    public boolean checkKey(String key) {
        return values.containsKey(key);
    }

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("List of parameters is empty");
        }
        for (String arg : args) {
            String[] pair = arg.split("=", 2);
            if (pair.length < 2) {
                throw new IllegalArgumentException(String.format("Parameter doesn't have equals symbol: %s", arg));
            }
            if (!pair[0].startsWith("-")) {
                throw new IllegalArgumentException(String.format("Wrong start of key in parameter: %s", arg));
            }
            if (pair[0].substring(1).isBlank()) {
                throw new IllegalArgumentException(String.format("Wrong key in parameter: %s", arg));
            }
            if (pair[1].isBlank()) {
                throw new IllegalArgumentException(String.format("Wrong argument in parameter: %s", arg));
            }
        }
    }

    private void parse(String[] args) {
        validate(args);
        for (String arg : args) {
            String[] pair = arg.split("=", 2);
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}