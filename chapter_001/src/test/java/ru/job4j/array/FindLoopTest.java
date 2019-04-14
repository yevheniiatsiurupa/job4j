package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 14/04/2019
 */

public class FindLoopTest {
    /**
     * Test indexOf.
     */
    @Test
    public void whenArrayHas5Then0() {
        FindLoop findLoop = new FindLoop();
        int[] input = {5, 10, 3};
        int value = 5;
        int result = findLoop.indexOf(input, value);
        int expected = 0;
        assertThat(result, is(expected));
    }

    /**
     * Test indexOf.
     */
    @Test
    public void whenArrayHas20ThenMinus1() {
        FindLoop findLoop = new FindLoop();
        int[] input = {5, 10, 3};
        int value = 20;
        int result = findLoop.indexOf(input, value);
        int expected = -1;
        assertThat(result, is(expected));
    }
}
