package ru.job4j.ood.srp.report;

import net.sf.saxon.expr.Component;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccounterGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(worker);
        Report engine = new ReportAccounter(store, parser, converter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(new InMemoryCurrencyConverter().convert(Currency.RUB, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Maks", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenDevGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportDev(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenJSONGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportJSON(store);
        StringBuilder expect = new StringBuilder();
        expect.append("[{\"name\":\"").append(worker.getName()).append("\",")
                .append("\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker.getSalary()).append("}]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenXmlReport() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder();
        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator())
                .append("\t<employees>").append(System.lineSeparator())
                .append("\t\t<fired>").append(now.get(Calendar.YEAR)).append("-").append(now.get(Calendar.MONTH) + 1)
                .append("-").append(now.get(Calendar.DAY_OF_MONTH)).append("T").append(now.get(Calendar.HOUR_OF_DAY))
                .append(":").append(now.get(Calendar.MINUTE)).append(":").append(now.get(Calendar.SECOND))
                .append(".").append(now.get(Calendar.MILLISECOND)).append("+03:00").append("</fired")
                .append(System.lineSeparator())
                .append("\t\t<hired>").append(now.get(Calendar.YEAR)).append("-").append(now.get(Calendar.MONTH) + 1)
                .append("-").append(now.get(Calendar.DAY_OF_MONTH)).append("T").append(now.get(Calendar.HOUR_OF_DAY))
                .append(":").append(now.get(Calendar.MINUTE)).append(":").append(now.get(Calendar.SECOND))
                .append(".").append(now.get(Calendar.MILLISECOND)).append("+03:00").append("</hired")
                .append(System.lineSeparator())
                .append("\t\t<name>").append(worker.getName()).append("</name>").append(System.lineSeparator())
                .append("\t\t<salary>").append(worker.getSalary()).append("</salary>").append(System.lineSeparator())
                .append("\t</employees>").append(System.lineSeparator()).append("</employees>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualToNormalizingPunctuationAndWhitespace(expect.toString());
    }
}