package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
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
        for (int i = 0; i < log.size(); i++) {
            String[] str = log.get(i).split(" ");
            if (!str[str.length - 2].equals("404")) {
                log.remove(i);
            }
        }
        return log;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
