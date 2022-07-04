package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (validate(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong numbers of parameteres. "
                    + "Usage java -jar search.jar ROOT_FOLDER FILE_EXTENSION");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Wrong parameter ROOT_FOLDER");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Wrong parameter FILE_EXTENSION");
        }
        return true;
    }
}
