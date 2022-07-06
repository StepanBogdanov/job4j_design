package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        Path[] files = sources.stream().toArray(Path[]::new);
        pack(target, files);
    }

    public void packSingleFile(Path source, Path target) {
        pack(target, source);
    }

    public void pack(Path target, Path... sources) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validate(ArgsName arg) {
        if (!arg.checkKey("d")) {
            throw new IllegalArgumentException("Enter source directory: -d=DIRECTORY");
        }
        if (!Files.isDirectory(Paths.get(arg.get("d")))) {
            throw new IllegalArgumentException("Wrong source directory");
        }
        if (!arg.checkKey("e")) {
            throw new IllegalArgumentException("Enter file extension: -e=FILE_EXTENSION");
        }
        if (!arg.checkKey("o")) {
            throw new IllegalArgumentException("Enter name of output archive: -o=NAME");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName parameters = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validate(parameters);
        List<Path> sources = Search.search(Paths.get(parameters.get("d")),
                p -> !p.toFile().getName().endsWith(parameters.get("e")));
        Path target = Paths.get(parameters.get("o"));
        zip.packFiles(sources, target);
    }
}