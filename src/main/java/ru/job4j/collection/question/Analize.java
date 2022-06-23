package ru.job4j.collection.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, String> users = new HashMap<>();
        for (User user : previous) {
            users.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!users.containsKey(user.getId())) {
                added++;
            } else if (!users.get(user.getId()).equals(user.getName())) {
                changed++;
            }
        }
        int deleted = previous.size() + added - current.size();
        Info info = new Info(added, changed, deleted);
        return info;
    }

}
