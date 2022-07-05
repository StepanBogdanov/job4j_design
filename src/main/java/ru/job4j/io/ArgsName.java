package ru.job4j.io;

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

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("List of parameters is empty");
        }
        for (String arg : args) {
            String[] pair = arg.split("=", 2);
            if (pair.length < 2) {
                throw new IllegalArgumentException("Parameter doesn't have equals symbol: " + arg);
            }
            if (!pair[0].startsWith("-")) {
                throw new IllegalArgumentException("Wrong start of key in parameter: " + arg);
            }
            if (pair[0].substring(1).isBlank()) {
                throw new IllegalArgumentException("Wrong key in parameter: " + arg);
            }
            if (pair[1].isBlank()) {
                throw new IllegalArgumentException("Wrong argument in parameter: " + arg);
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

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}