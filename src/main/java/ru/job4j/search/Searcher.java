package ru.job4j.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {
    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }

    private static void validate(ArgsName argsName) {
        if (!argsName.checkKey("d") || !Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Enter source directory: -d=DIRECTORY");
        }
        if (!argsName.checkKey("n")) {
            throw new IllegalArgumentException("Enter searching name, mask or regex: -n=NAME");
        }
        if (!argsName.checkKey("t") || (!"name".equals(argsName.get("t"))
                && !"mask".equals(argsName.get("t")) && !"regex".equals(argsName.get("t")))) {
            throw new IllegalArgumentException("Enter type of search: -t=name or -t=mask or -t=regex");
        }
        if (!argsName.checkKey("o")) {
            throw new IllegalArgumentException("Enter output file: -o=FILENAME");
        }
    }

    private static Predicate<Path> getPredicate(ArgsName argsName) {
        Predicate<Path> predicate;
        if ("name".equals(argsName.get("t"))) {
            predicate = p -> p.toFile().getName().equals(argsName.get("n"));
        } else if ("mask".equals(argsName.get("t"))) {
            String regex = argsName.get("n").replace("*", "\\w+").replace("?", "\\w");
            predicate = p -> p.toFile().getName().matches(regex);
        } else {
            predicate = p -> p.toFile().getName().matches(argsName.get("n"));
        }
        return predicate;
    }

    private static void handle(ArgsName argsName) throws IOException {
        Predicate<Path> condition = getPredicate(argsName);
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(Paths.get(argsName.get("d")), searcher);
        List<Path> list = searcher.getPaths();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("o")))) {
            for (Path path : list) {
                writer.write(path.toAbsolutePath().normalize().toString());
                writer.newLine();
            }
        }
    }
}
