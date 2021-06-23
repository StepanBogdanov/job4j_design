package ru.job4j.io.serialization.json.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {

    @XmlAttribute
    private int power;

    @XmlAttribute
    private String type;

    public Engine() {
    }

    public Engine(int power, String type) {
        this.power = power;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "power=" + power +
                ", type='" + type + '\'' +
                '}';
    }
}
