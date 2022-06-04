package ru.job4j.generics;

import org.junit.Test;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleNameIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Mentor"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Mentor"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindURoleNameIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Mentor"));
        store.add(new Role("1", "Maxim", "Student"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }

    @Test
    public void whenReplaceThenRoleNameIsStudent() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Mentor"));
        store.replace("1", new Role("1", "Maxim", "Student"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Student"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Mentor"));
        store.replace("10", new Role("10", "Maxim", "Student"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }

    @Test
    public void whenDeleteRoleThenURoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Mentor"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr", "Mentor"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mentor"));
    }
}