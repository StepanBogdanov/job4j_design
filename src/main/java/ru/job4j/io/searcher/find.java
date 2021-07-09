package ru.job4j.io.searcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class find {
    public static void main(String[] args) throws IOException {
        boolean validate = true;

        if (args.length < 4) {
            System.out.println("Неверные ключи: " + System.lineSeparator() +
                    "-d - директория, в которой начинать поиск," + System.lineSeparator() +
                    "-n - имя файла, маска, либо регулярное выражение," + System.lineSeparator() +
                    "-t - тип поиска: mask искать по маске, name по полному совпадение имени, " +
                    "regex по регулярному выражению" + System.lineSeparator() +
                    "-o - результат записать в файл.");
            validate = false;
        }

        ArgsName keys = ArgsName.of(args);

        File dir = new File(keys.get("d"));
        if (!dir.isDirectory()) {
            System.out.println("Неверно указана директория -d");
            validate = false;
        }
        if (!keys.get("t").equals("name") && !keys.get("t").equals("mask") && !keys.get("t").equals("regex")) {
            System.out.println("Невверно указан тип поиска -t");
            validate = false;
        }

        if (validate) {
            List<Path> result = new ArrayList<>(Search.search(dir.toPath(), getPredicate(keys.get("t"), keys.get("n"))));
            writer(keys.get("o"), result);
        }
    }

    private static Predicate<Path> getPredicate(String searchType, String name) {
        if (searchType.equals("mask")) {
            return p -> p.toFile().getName().contains(name);
        } else if (searchType.equals("name")) {
            return p -> p.toFile().getName().equals(name);
        } else {
            Pattern pattern = Pattern.compile(name);
            return p -> pattern.matcher(p.toFile().getName()).find();
        }
    }

    private static void writer(String fileName, List<Path> list) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))) {
            for (Path path : list) {
                writer.write(path.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
