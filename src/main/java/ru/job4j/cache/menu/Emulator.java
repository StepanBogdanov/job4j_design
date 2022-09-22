package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.io.File;
import java.util.Scanner;

public class Emulator {

    public static final int LOAD = 1;
    public static final int GET = 2;

    public static final String SELECT = "Выберите меню";
    public static final String DIR = "Укажите кэшируемую директорию";
    public static final String NAME = "Введите название файла";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, чтобы загрузить содержимое файла в кэш.
                Введите 2, чтобы получить содежимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        AbstractCache cache;
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        String dir;
        do {
            System.out.println(DIR);
            dir = scanner.nextLine();
        } while (!(new File(dir).isDirectory()));
        cache = new DirFileCache(dir);
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (LOAD == userChoice) {
                System.out.println(NAME);
                String key = scanner.nextLine();
                String value = (String) cache.get(key);
                cache.put(key, value);
            } else if (GET == userChoice) {
                System.out.println(NAME);
                String key = scanner.nextLine();
                System.out.println(cache.get(key));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
