package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {

    public static void main(String[] args) {

        int[][] table = new int[10][10];
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                table[i - 1][j - 1] = i * j;
            }
        }

        try (FileOutputStream out = new FileOutputStream("table.txt")) {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    out.write(String.valueOf(table[i][j]).getBytes());
                    out.write("\t".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
