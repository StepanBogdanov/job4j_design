package ru.job4j.collection.analize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user.name);
        }
        for (User user : current) {
            String s = map.put(user.id, user.name);
            if (s == null) {
                info.added++;
            } else if (!s.equals(user.name)) {
                info.changed++;
            }
        }
        info.deleted = previous.size() + info.added - current.size();
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {

        int added;
        int changed;
        int deleted;

    }
}


