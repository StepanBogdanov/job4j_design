package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder or file extension is null. "
                    + "Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION.");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static class SearchFiles extends SimpleFileVisitor<Path> {

        private final Predicate<Path> condition;
        private final List<Path> files = new ArrayList<>();

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (condition.test(file)) {
                files.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        public List<Path> getPaths() {
            return files;
        }
    }
}
