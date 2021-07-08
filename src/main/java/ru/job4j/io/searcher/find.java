package ru.job4j.io.searcher;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class find {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Неверные ключи: " + System.lineSeparator() +
                    "-d - директория, в которой начинать поиск," + System.lineSeparator() +
                    "-n - имя файла, маска, либо регулярное выражение," + System.lineSeparator() +
                    "-t - тип поиска: mask искать по маске, name по полному совпадение имени, " +
                    "regex по регулярному выражению" + System.lineSeparator() +
                    "-o - результат записать в файл.");
        }

        ArgsName keys = ArgsName.of(args);

        File dir = new File(keys.get("d"));
        if (!dir.isDirectory()) {
            System.out.println("Неверно указана директория -d");
        }

        try (FileOutputStream out = new FileOutputStream(keys.get("o"))) {
            List<Path> result = new ArrayList<>();
            if (keys.get("t").equals("mask")) {
                result.addAll(Search.search(dir.toPath(), p -> p.toFile().getName().contains(keys.get("n"))));
            }
            if (keys.get("t").equals("name")) {
                result.addAll(Search.search(dir.toPath(), p -> p.toFile().getName().equals(keys.get("n"))));
            }
            if (keys.get("t").equals("regex")) {
                Pattern pattern = Pattern.compile(keys.get("n"));
                result.addAll(Search.search(dir.toPath(), p -> pattern.matcher(p.toFile().getName()).find()));
            }
            for (Path path : result) {
                out.write(path.toString().getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
