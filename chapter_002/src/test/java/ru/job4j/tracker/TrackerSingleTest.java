package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleTest {
    /**
     * Test TrackerSingle1.
     */
    @Test
    public void whenMakeNewObjectThenSameObject1() {
        TrackerSingle1 first = TrackerSingle1.INSTANCE;
        TrackerSingle1 second = TrackerSingle1.INSTANCE;
        assertThat(first, is(second));
    }

    /**
     * Test TrackerSingle2.
     */
    @Test
    public void whenMakeNewObjectThenSameObject2() {
        TrackerSingle2 first = TrackerSingle2.getInstance();
        TrackerSingle2 second = TrackerSingle2.getInstance();
        assertThat(first, is(second));
    }

    /**
     * Test TrackerSingle3.
     */
    @Test
    public void whenMakeNewObjectThenSameObject3() {
        TrackerSingle3 first = TrackerSingle3.getInstance();
        TrackerSingle3 second = TrackerSingle3.getInstance();
        assertThat(first, is(second));
    }

    /**
     * Test TrackerSingle4.
     */
    @Test
    public void whenMakeNewObjectThenSameObject4() {
        TrackerSingle4 first = TrackerSingle4.getInstance();
        TrackerSingle4 second = TrackerSingle4.getInstance();
        assertThat(first, is(second));
    }
}
