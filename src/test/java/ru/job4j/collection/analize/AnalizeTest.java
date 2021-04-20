package ru.job4j.collection.analize;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.List;

public class AnalizeTest {

    @Test
    public void whenAdd() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(new Analize.User(1, "Alex"),
                new Analize.User(2, "Tom")), List.of(new Analize.User(3, "Paul")));
        int rsl = info.added;
        assertThat(rsl, is(1));
    }

    @Test
    public void whenChange() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(new Analize.User(1, "Alex"),
                new Analize.User(2, "Tom")), List.of(new Analize.User(2, "Paul")));
        int rsl = info.changed;
        assertThat(rsl, is(1));
    }

    @Test
    public void whenDel() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(new Analize.User(1, "Alex"),
                new Analize.User(2, "Tom")), List.of(new Analize.User(1, "Alex")));
        int rsl = info.deleted;
        assertThat(rsl, is(1));
    }

}