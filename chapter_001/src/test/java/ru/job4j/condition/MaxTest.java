package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 10/04/2019
 */
public class MaxTest {
    /**
     * Test max.
     */
    @Test
    public void whenMax1to2Then2() {
        Max max = new Max();
        int result = max.max(1, 2);
        assertThat(result, is(2));
    }

    /**
     * Test max.
     */
    @Test
    public void whenMax3to1Then3() {
        Max max = new Max();
        int result = max.max(3, 1);
        assertThat(result, is(3));
    }

    /**
     * Test max.
     */
    @Test
    public void whenMax4to4Then4() {
        Max max = new Max();
        int result = max.max(4, 4);
        assertThat(result, is(4));
    }
}
