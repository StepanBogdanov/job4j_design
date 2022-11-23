package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements Report {

    private Store store;
    private Marshaller marshaller;

    public XmlReport(Store store) {
        try {
            this.store = store;
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            this.marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            throw new IllegalArgumentException();
        }
        return xml;
    }

    @XmlRootElement (name = "xmlReport")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Employees {

        @XmlElementWrapper (name = "employees")
        @XmlElement (name = "employee")
        private List<Employee> employees;

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public Employees() {
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
