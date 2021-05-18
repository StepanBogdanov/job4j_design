package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target, int length) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path source : sources) {
                packSingleFile(source, zip, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, ZipOutputStream zip, int length) {
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.putNextEntry(new ZipEntry(source.toString().substring(length)));
                zip.write(out.readAllBytes());
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Wrong parameters");
        }

        ArgsName parameters = ArgsName.of(args);

        List<Path> files = Search.search(Paths.get(parameters.get("d")),
                    p -> !p.toFile().getName().endsWith(parameters.get("e")));

        int pathLength = parameters.get("d").length();

        new Zip().packFiles(files, Paths.get(parameters.get("o")), pathLength);

    }
}
