package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final List<String> answers = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String chooseAnswer(List<String> answers) {
        return answers.get((new Random()).nextInt(answers.size()));
    }

    public void run() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true));
             Scanner in = new Scanner(System.in)) {
            String question = in.nextLine();
            while (!question.endsWith(OUT)) {
                if (question.equals(STOP)) {
                    pw.write(question + System.lineSeparator());
                    question = in.nextLine();
                    while (!question.equals(CONTINUE)) {
                        pw.write(question + System.lineSeparator());
                        question = in.nextLine();
                    }
                }
                pw.write(question + System.lineSeparator());
                String answer = chooseAnswer(answers);
                System.out.println(answer);
                pw.write(answer + System.lineSeparator());
                question = in.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("Log_chat.txt", "answers.txt");
        cc.run();
    }
}
