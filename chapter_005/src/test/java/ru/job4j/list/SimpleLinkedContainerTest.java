package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 09/06/2019
 */
public class SimpleLinkedContainerTest {
    /**
     * Test add / get.
     */
    @Test
    public void whenAddItemToLinkedThenCanGetItem() {
        SimpleLinkedContainer<String> test = new SimpleLinkedContainer<>();
        test.add("001");
        test.add("002");
        assertThat(test.get(0), is("001"));
        assertThat(test.get(1), is("002"));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIterateItemToLinkedThenHasNext() {
        SimpleLinkedContainer<String> test = new SimpleLinkedContainer<>();
        test.add("001");
        test.add("002");
        test.add("003");
        Iterator<String> iter = test.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}