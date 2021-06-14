package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsageJson {
    public static void main(String[] args) {
        Writer writer = new Writer(true, 2, new Book("War"), new Book("Peace"));

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(writer));

        final String writerJson =
                "{"
                + "\"sex\":true,"
                + "\"numberOfBooks\":2,"
                + "\"books\":"
                    + "["
                        + "{"
                            + "\"title\":\"War\""
                        + "},"
                        + "{"
                            + "\"title\":\"Peace\""
                        + "}"
                    + "]"
                + "}";

        final Writer writerMod = gson.fromJson(writerJson, Writer.class);
        System.out.println(writerMod);
    }
}
