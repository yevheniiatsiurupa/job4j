package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class PriorityOueueTest {
    /**
     * Test PriorityQueue.
     */
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("initial_1", 0));
        queue.put(new Task("initial_2", 1));
        queue.put(new Task("low", 2));
        queue.put(new Task("urgent", 0));
        queue.put(new Task("middle", 1));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}
