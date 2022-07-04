package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        files.computeIfAbsent(new FileProperty(Files.size(file), file.getFileName().toString()),
                key -> new ArrayList<>()).add(file);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getFiles() {
        return files;
    }
}
