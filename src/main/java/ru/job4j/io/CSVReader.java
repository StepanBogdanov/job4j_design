package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }

    public static void handle(ArgsName argsName) throws Exception {
        List<Integer> filter = filter(argsName);
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")));
                PrintStream out = "stdout".equals(argsName.get("out")) ? new PrintStream(System.out)
                : new PrintStream(new FileOutputStream(argsName.get("out")))) {
            scanner.useDelimiter(System.lineSeparator());
            while (scanner.hasNext()) {
                String[] line = scanner.next().split(argsName.get("delimiter"));
                String[] filteredLine = new String[filter.size()];
                int ind = 0;
                for (Integer i : filter) {
                    filteredLine[ind++] = line[i];
                }
                out.print(String.join(argsName.get("delimiter"), filteredLine));
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        if (!argsName.checkKey("path")) {
            throw new IllegalArgumentException("Enter source file: -path=FILE");
        }
        if (!argsName.checkKey("delimiter")) {
            throw new IllegalArgumentException("Enter delimiter: -delimiter=DELIMITER");
        }
        if (!argsName.checkKey("out")) {
            throw new IllegalArgumentException("Enter type of out: -out=OUT");
        }
        if (!argsName.checkKey("filter")) {
            throw new IllegalArgumentException("Enter filter: -filter=FILTER");
        }
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException("Enter correct source file name: -path=FILE");
        }
        if (!("stdout".equals(argsName.get("out")) || argsName.get("out").endsWith(".csv"))) {
            throw new IllegalArgumentException("Enter correct out: -out=OUT");
        }
    }

    private static List<Integer> filter(ArgsName argsName) {
        List<Integer> filteredColumn = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            scanner.useDelimiter(System.lineSeparator());
            String[] title = scanner.next().split(argsName.get("delimiter"));
            List<String> filter = new ArrayList<>(List.of(argsName.get("filter").split(",")));
            for (int i = 0; i < title.length; i++) {
                if (filter.contains(title[i])) {
                    filteredColumn.add(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredColumn;
    }
}
