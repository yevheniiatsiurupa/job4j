package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 09/06/2019
 */

public class SimpleQueueTest {
    /**
     * Test SimpleQueue.
     */
    @Test
    public void whenFirstInThenFirstOut() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

    @Test
    public void name() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        Integer a = queue.poll();
        queue.push(4);
        Integer b = queue.poll();
        assertThat(a, is(1));
        assertThat(b, is(2));
    }
}