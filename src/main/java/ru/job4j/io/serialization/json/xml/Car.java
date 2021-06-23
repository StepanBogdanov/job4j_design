package ru.job4j.io.serialization.json.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private String model;

    @XmlAttribute
    private boolean isNew;

    @XmlAttribute
    private int passengers;
    private Engine engine;

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private String[] options;

    public Car() {
    }

    public Car(String model, boolean isNew, int passengers, Engine engine, String... options) {
        this.model = model;
        this.isNew = isNew;
        this.passengers = passengers;
        this.engine = engine;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", isNew=" + isNew +
                ", passengers=" + passengers +
                ", engine=" + engine +
                ", options=" + Arrays.toString(options) +
                '}';
    }
}

