package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsageJSON {
    public static void main(String[] args) {

        JSONObject jsonFaculty = new JSONObject("{\"name\":\"Math\"}");

        List<String> list = new ArrayList<>();
        list.add("Smart");
        list.add("Fun");
        JSONArray jsonCharacterizations = new JSONArray(list);

        final Student student = new Student("Ivan", 25, true, new Faculty("Math"),
                new String[] {"Smart", "Fun"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", student.getName());
        jsonObject.put("age", student.getAge());
        jsonObject.put("sex", student.isSex());
        jsonObject.put("faculty", jsonFaculty);
        jsonObject.put("charactrizations", jsonCharacterizations);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(student).toString());
    }
}
