package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicate = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicate);
        duplicate.getFiles().entrySet().stream().
        filter(s -> s.getValue().size() > 1).
        forEach(s -> {
            System.out.println(String.format("%s - %d byte", s.getKey().getName(), s.getKey().getSize()));
            for (Path path : s.getValue()) {
                System.out.println(path.toAbsolutePath().normalize());
            }
        });
    }
}
