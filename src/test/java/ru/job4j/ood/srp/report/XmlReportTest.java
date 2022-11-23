package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.XmlReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class XmlReportTest {
    @Test
    public void whenXmlReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> formatter = new XmlReportDateTimeParser();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XmlReport(store);
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .add("<xmlReport>")
                .add("\t<employees>")
                .add("\t\t<employee>")
                .add(String.format("\t\t\t<name>%s</name>", worker.getName()))
                .add(String.format("\t\t\t<hired>%s</hired>", formatter.parse(worker.getHired())))
                .add(String.format("\t\t\t<fired>%s</fired>", formatter.parse(worker.getFired())))
                .add(String.format("\t\t\t<salary>%s</salary>", worker.getSalary()))
                .add("\t\t</employee>")
                .add("\t</employees>")
                .add("</xmlReport>");
        assertThat(engine.generate(em -> true)).isEqualToNormalizingPunctuationAndWhitespace(expected.toString());
    }
}