package ru.job4j.ood.ocp.fail;

public class Store {

    public void save(Item item) {
        if (item instanceof Item1) {
            System.out.println(item);
        } else if (item instanceof Item2) {
            System.out.println(item);
        }
    }
    /*
    Каждый раз при добавлении или удалении наследников Item, будет изменяться класс Store
     */
}

abstract class Item {

}

class Item1 extends Item {

}

class Item2 extends Item {

}
