package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        Scanner in = new Scanner(System.in);
        boolean readyToAnswer = true;
        String phrase = in.nextLine();
        while (!OUT.equals(phrase)) {
            log.add(phrase);
            if (!readyToAnswer && CONTINUE.equals(phrase)) {
                readyToAnswer = true;
            }
            if (readyToAnswer && STOP.equals(phrase)) {
                readyToAnswer = false;
            }
            if (readyToAnswer) {
                String answer = answers.get((int) (Math.random() * answers.size()));
                System.out.println(answer);
                log.add(answer);
            }
            phrase = in.nextLine();
        }
        log.add(phrase);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(botAnswers))) {
            read.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log.txt", "answers.txt");
        cc.run();
    }
}