package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/04/2019
 */
public class CounterTest {
    /**
     * Test add.
     */
    @Test
    public void whenSumFromOneToTenThenThirty() {
        Counter countnumb = new Counter();
        int result = countnumb.add(1, 10);
        assertThat(result, is(30));
    }
}
