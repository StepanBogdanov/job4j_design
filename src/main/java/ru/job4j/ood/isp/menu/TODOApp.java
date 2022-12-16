package ru.job4j.ood.isp.menu;

import net.sf.saxon.expr.Component;

import java.util.Scanner;

public class TODOApp {

    public static final String MENU = String.join(System.lineSeparator(),
            "1.Ввести задачу",
            "2.Вывести список задач",
            "3.Выход" + System.lineSeparator());
    public static final String ASK = "Выберите пункт меню";
    public static final ActionDelegate STUB_ACTION = System.out::println;


    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        Scanner sc = new Scanner(System.in);
        System.out.println(MENU);
        System.out.println(ASK);
        int enter = Integer.parseInt(sc.nextLine());
        while (enter != 3) {
            switch (enter) {
                case 1:
                    System.out.println("Введите задачу");
                    String parent = sc.nextLine();
                    if (parent.equals("")) {
                        parent = null;
                    }
                    System.out.println("Введите подзадачу");
                    String child = sc.nextLine();
                    menu.add(parent, child, STUB_ACTION);
                    break;
                case 2:
                    printer.print(menu);
                    break;
                default:
                    System.out.println("Выберите корректный пункт меню");
            }
            System.out.println(MENU);
            System.out.println(ASK);
            enter = Integer.parseInt(sc.nextLine());
        }
    }
}
