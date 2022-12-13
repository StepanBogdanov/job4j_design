package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            String number = menuItemInfo.getNumber();
            long dotCount = number.chars().filter(ch -> ch == '.').count();
            for (int i = 1; i < dotCount; i++) {
                System.out.print("\t");
            }
            System.out.println(menuItemInfo.getNumber() + menuItemInfo.getName());
        }
    }
}
