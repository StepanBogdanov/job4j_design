package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            StringBuilder unavailable = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                String[] status = line.split(" ");
                if ((status[0].equals("400") || status[0].equals("500")) && unavailable.length() == 0) {
                    unavailable.append(status[1]);
                    unavailable.append(';');
                } else if ((status[0].equals("200") || status[0].equals("300")) && unavailable.length() != 0) {
                    unavailable.append(status[1]);
                    out.write(unavailable.toString());
                    out.write(System.lineSeparator());
                    unavailable.setLength(0);
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
