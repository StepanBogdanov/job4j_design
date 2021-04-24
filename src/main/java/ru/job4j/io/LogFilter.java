package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> log = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            log =  reader.lines().filter(str -> str.contains(" 404 ")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log;
    }

    public static void save(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("log_result.txt")))) {
            for (String s : log) {
                out.write(s);
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log);
    }
}
