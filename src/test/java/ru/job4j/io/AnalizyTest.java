package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenOneDiapason() throws IOException {
        File source = folder.newFile("status.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10.14.22");
            out.println("400 10.15.45");
            out.println("500 10.15.55");
            out.println("300 10.16.23");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10.15.45;10.16.23"));
    }

    @Test
    public void whenTwoDiapasons() throws IOException {
        File source = folder.newFile("status.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10.14.22");
            out.println("400 10.15.45");
            out.println("300 10.16.23");
            out.println("500 10.17.33");
            out.println("200 10.20.30");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10.15.45;10.16.2310.17.33;10.20.30"));
    }

    @Test
    public void whenNoDiapason() throws IOException {
        File source = folder.newFile("status.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10.14.22");
            out.println("300 10.16.23");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is(""));
    }
}