package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            for (String number : numbers) {
                if (Integer.parseInt(number) % 2 == 0) {
                    System.out.println(number + "  is even");
                } else {
                    System.out.println(number + " isn't even");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
