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
    private final List<String> chatLog = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private void readAnswers(String botAnswers) {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLog(List<String> chatLog, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String s : chatLog) {
                pw.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String chooseAnswer(List<String> answers) {
        return answers.get((new Random()).nextInt(answers.size()));
    }

    public void run() {
        readAnswers(botAnswers);
        Scanner in = new Scanner(System.in);
        String question = in.nextLine();
        while (!question.endsWith(OUT)) {
            if (question.equals(STOP)) {
                chatLog.add(question);
                question = in.nextLine();
                while (!question.equals(CONTINUE)) {
                    chatLog.add(question);
                    question = in.nextLine();
                }
            }
            chatLog.add(question);
            String answer = chooseAnswer(answers);
            System.out.println(answer);
            chatLog.add(answer);
            question = in.nextLine();
        }
        chatLog.add(question);
        writeLog(chatLog, path);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("Log_chat.txt", "answers.txt");
        cc.run();
    }
}
