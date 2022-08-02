package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private boolean sex;
    private Faculty faculty;

    @XmlElementWrapper(name = "characterizations")
    @XmlElement(name = "characterization")
    private String[] characterization;

    public Student() {

    }

    public Student(String name, int age, boolean sex, Faculty faculty, String[] characterization) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.faculty = faculty;
        this.characterization = characterization;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String[] getCharacterization() {
        return characterization;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
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
