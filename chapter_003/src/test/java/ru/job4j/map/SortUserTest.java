package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 18/05/2019
 */

public class SortUserTest {
    /**
     * Test sort.
     */
    @Test
    public void whenAddUsersThenSorted() {
        List<User> list = new ArrayList<>();
        User one = new User("Peter", 35);
        User two = new User("Anna", 13);
        User three = new User("Liza", 25);
        list.add(one);
        list.add(two);
        list.add(three);
        SortUser sortUser = new SortUser();
        Set<User> rst = sortUser.sort(list);
        User[] result = new User[rst.size()];
        rst.toArray(result);
        User[] expected = new User[] {two, three, one};
        assertThat(result, is(expected));
    }

    /**
     * Test sort when same age.
     */
    @Test
    public void whenAddSameAgeThenSortedByName() {
        List<User> list = new ArrayList<>();
        User one = new User("Peter", 35);
        User two = new User("Liza", 13);
        User three = new User("Anna", 13);
        list.add(one);
        list.add(two);
        list.add(three);
        SortUser sortUser = new SortUser();
        Set<User> rst = sortUser.sort(list);
        User[] result = new User[rst.size()];
        rst.toArray(result);
        User[] expected = new User[] {three, two, one};
        assertThat(result, is(expected));
    }

    /**
     * Test sortNameLength.
     */
    @Test
    public void whenAddUsersThenSortByNameLength() {
        List<User> list = new ArrayList<>();
        User one = new User("Peter", 35);
        User two = new User("Joe", 13);
        User three = new User("Anna", 13);
        list.add(one);
        list.add(two);
        list.add(three);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(list);
        List<User> expected = new ArrayList<>(Arrays.asList(two, three, one));
        assertThat(result, is(expected));
    }

    /**
     * Test sortNameLength.
     */
    @Test
    public void whenAddUsersThenSortByAllFields() {
        List<User> list = new ArrayList<>();
        User one = new User("Peter", 35);
        User two = new User("Anna", 15);
        User three = new User("Anna", 14);
        list.add(one);
        list.add(two);
        list.add(three);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(list);
        List<User> expected = new ArrayList<>(Arrays.asList(three, two, one));
        assertThat(result, is(expected));
    }
}
