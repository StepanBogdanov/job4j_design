package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsageGson {
    public static void main(String[] args) {
        final Student student = new Student("Ivan", 25, true, new Faculty("Math"),
                new String[] {"Smart", "Fun"});

        final Gson gson = new GsonBuilder().create();
        String studentJson = gson.toJson(student);

        final Student student2 = gson.fromJson(studentJson, Student.class);
        System.out.println(student2);
    }
}

