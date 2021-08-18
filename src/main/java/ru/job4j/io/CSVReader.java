package ru.job4j.io;

import java.io.*;
import java.util.Scanner;

public class CSVReader {

    public static void main(String[] args) throws FileNotFoundException {

        boolean validate = true;

        if (args.length < 4) {
            System.out.println("Неверные ключи: " + System.lineSeparator() +
                    "-path - путь к файлу" + System.lineSeparator() +
                    "-delimiter - разделитель" + System.lineSeparator() +
                    "-out - приемник данных" + System.lineSeparator() +
                    "-filter - фильтр по столбцам.");
            validate = false;
        }

        ArgsName keys = ArgsName.of(args);

        var file = new File(keys.get("path"));
        if (!file.exists()) {
            System.out.println("Файл не существует");
            validate = false;
        }

        if (validate) {
            if (keys.get("out").equals("stdout")) {
                out(keys, file, System.out);
            } else {
                try (PrintStream stream = new PrintStream(keys.get("out"))) {
                    out(keys, file, stream);
                }
            }
        }

    }

    private static int[] getIndexes(File file, String filter, String delimiter) throws FileNotFoundException {
        int count = 0;
        int[] temp;
        try (var sc = new Scanner(file)) {
            String[] filters = filter.split(",");
            String[] firstLine = sc.nextLine().split(delimiter);
            temp = new int[firstLine.length];
            for (int i = 0; i < firstLine.length; i++) {
                for (String s : filters) {
                    if (firstLine[i].contains(s)) {
                        temp[count++] = i;
                    }
                }
            }
        }
        int[] indexes = new int[count];
        System.arraycopy(temp, 0, indexes, 0, count);
        return indexes;
    }

    private static void out(ArgsName keys, File file, PrintStream stream) throws FileNotFoundException {
        int[] indexes = getIndexes(file, keys.get("filter"), keys.get("delimiter"));
        try (var sc = new Scanner(file)){
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(keys.get("delimiter"));
                for (int index : indexes) {
                    stream.print(line[index]);
                }
            stream.println();
            }
        }
    }
}
