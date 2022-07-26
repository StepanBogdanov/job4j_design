package ru.job4j.serialization.json;

import java.util.Arrays;

public class Student {
    private String name;
    private int age;
    private boolean sex;
    private Faculty faculty;
    private String[] characterization;

    public Student(String name, int age, boolean sex, Faculty faculty, String[] characterization) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.faculty = faculty;
        this.characterization = characterization;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex
                + ", faculty=" + faculty
                + ", characterization=" + Arrays.toString(characterization)
                + '}';
    }
}
