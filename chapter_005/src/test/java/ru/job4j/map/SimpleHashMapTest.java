package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/06/2019
 */

public class SimpleHashMapTest {
    /**
     * Test insert / get.
     */
    @Test
    public void whenAddTwoElementsThenSizeIsTwo() {
        SimpleHashMap<User, String> testMap = new SimpleHashMap<>();
        Calendar firstDate = new GregorianCalendar(1990, 2, 10);
        Calendar secondDate = new GregorianCalendar(1990, 2, 10);
        User first = new User("John", 2, firstDate);
        User second = new User("Jack", 2, secondDate);
        testMap.insert(first, "001");
        testMap.insert(second, "002");
        assertThat(testMap.size(), is(2));
        assertThat(testMap.get(second), is("002"));
    }

    /**
     * Test resize.
     */
    @Test
    public void whenAddFiveElementsThenSizeIsFive() {
        SimpleHashMap<Integer, String> testMap = new SimpleHashMap<>();
        testMap.insert(0, "000");
        testMap.insert(1, "001");
        testMap.insert(2, "002");
        testMap.insert(3, "003");
        testMap.insert(4, "004");
        assertThat(testMap.size(), is(5));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteElementsThenSizeIsTwo() {
        SimpleHashMap<Integer, String> testMap = new SimpleHashMap<>();
        testMap.insert(1, "001");
        testMap.insert(2, "002");
        testMap.insert(3, "003");
        testMap.delete(2);
        assertThat(testMap.size(), is(2));
    }

    /**
     * Test insertNull.
     */
    @Test
    public void whenAddNullTwiceThenNullOnce() {
        SimpleHashMap<Integer, String> testMap = new SimpleHashMap<>();
        testMap.insert(null, "000");
        testMap.insert(null, "001");
        testMap.insert(2, "002");
        assertThat(testMap.size(), is(2));
    }

    /**
     * Test getNull.
     */
    @Test
    public void whenAddNullThenGetValue() {
        SimpleHashMap<Integer, String> testMap = new SimpleHashMap<>();
        testMap.insert(1, "000");
        testMap.insert(null, "001");
        testMap.insert(2, "002");
        assertThat(testMap.get(null), is("001"));
    }

    /**
     * Test deleteNull.
     */
    @Test
    public void whenDeleteNullThenSizeTwo() {
        SimpleHashMap<Integer, String> testMap = new SimpleHashMap<>();
        testMap.insert(1, "000");
        testMap.insert(null, "001");
        testMap.insert(2, "002");
        testMap.delete(null);
        assertThat(testMap.size(), is(2));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIterateThenShowAllValuesAndKeys() {
        SimpleHashMap<Integer, String> testMap = new SimpleHashMap<>();
        testMap.insert(0, "000");
        testMap.insert(1, "001");
        testMap.insert(2, "002");
        testMap.insert(3, "003");
        testMap.insert(4, "004");
        SimpleHashMap.KeyIterator testIt = testMap.new KeyIterator();
        while (testIt.hasNext()) {
            System.out.println(testIt.next());
        }
        SimpleHashMap.ValueIterator testIt2 = testMap.new ValueIterator();
        while (testIt2.hasNext()) {
            System.out.println(testIt2.next());
        }


    }
}