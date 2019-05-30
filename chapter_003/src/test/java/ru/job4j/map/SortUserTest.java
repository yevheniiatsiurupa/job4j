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
        User one = new User("Peter", 35);
        User two = new User("Anna", 13);
        User three = new User("Liza", 25);
        List<User> list = List.of(one, two, three);
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
        User one = new User("Peter", 35);
        User two = new User("Liza", 13);
        User three = new User("Anna", 13);
        List<User> list = List.of(one, two, three);
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
        User one = new User("Peter", 35);
        User two = new User("Joe", 13);
        User three = new User("Anna", 13);
        List<User> list = Arrays.asList(one, two, three);
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
        User one = new User("Peter", 35);
        User two = new User("Anna", 15);
        User three = new User("Anna", 14);
        List<User> list = Arrays.asList(one, two, three);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(list);
        List<User> expected = new ArrayList<>(Arrays.asList(three, two, one));
        assertThat(result, is(expected));
    }
}
