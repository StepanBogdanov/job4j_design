package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsageJson {
    public static void main(String[] args) {
//        Writer writer = new Writer(true, 2, new Book("War"), new Book("Peace"));
//
//        final Gson gson = new GsonBuilder().create();
//        System.out.println(gson.toJson(writer));
//
//        final String writerJson =
//                "{"
//                + "\"sex\":true,"
//                + "\"numberOfBooks\":2,"
//                + "\"books\":"
//                    + "["
//                        + "{"
//                            + "\"title\":\"War\""
//                        + "},"
//                        + "{"
//                            + "\"title\":\"Peace\""
//                        + "}"
//                    + "]"
//                + "}";
//
//        final Writer writerMod = gson.fromJson(writerJson, Writer.class);
//        System.out.println(writerMod);

        JSONObject jsonBook1 = new JSONObject("{\"title\":\"War\"}");
        JSONObject jsonBook2 = new JSONObject("{\"title\":\"Peace\"}");

        List<JSONObject> list = new ArrayList<>();
        list.add(jsonBook1);
        list.add(jsonBook2);
        JSONArray jsonBooks = new JSONArray(list);

        final Writer writer = new Writer(true, 2, new Book("War"), new Book("Peace"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", writer.isSex());
        jsonObject.put("numberOfBooks", writer.getNumberOfBooks());
        jsonObject.put("books", jsonBooks);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(writer).toString());
    }
}
