package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 05/06/2019
 */

public class SimpleArrayTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddElementThenArrayHasElement() {
        SimpleArray<Integer>  simpleArray = new SimpleArray<>(2);
        simpleArray.add(3);
        assertThat(simpleArray.get(0), is(3));
    }

    /**
     * Test set.
     */
    @Test
    public void whenSetElementThenElementChange() {
        SimpleArray<Integer>  simpleArray = new SimpleArray<>(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.set(0, 5);
        assertThat(simpleArray.get(0), is(5));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenRemoveElementThenElementDeleted() {
        SimpleArray<Integer>  simpleArray = new SimpleArray<>(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.remove(0);
        assertThat(simpleArray.get(0), is(4));
    }
}