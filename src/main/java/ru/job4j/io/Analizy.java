package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            String start = "";
            while ((line = read.readLine()) != null) {
                String[] splitLine = line.split(" ");
                if (start.isEmpty() && (splitLine[0].equals("400") || splitLine[0].equals("500"))) {
                    start = splitLine[1];
                    continue;
                }
                if (!start.isEmpty() && !splitLine[0].equals("400") && !splitLine[0].equals("500")) {
                    out.println(start + ";" + splitLine[1]);
                    start = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }