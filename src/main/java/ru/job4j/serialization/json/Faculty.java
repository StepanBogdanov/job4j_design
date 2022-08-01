package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "faculty")
public class Faculty {

    @XmlAttribute
    private String name;

    public Faculty() {

    }

    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Faculty{"
                + "name='" + name + '\''
                + '}';
    }
}
