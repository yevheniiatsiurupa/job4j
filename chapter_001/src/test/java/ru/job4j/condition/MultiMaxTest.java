package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 11/04/2019
 */

public class MultiMaxTest {

    /**
     * Test max.
     */
    @Test
    public void whenFirstMax() {
        MultiMax check = new MultiMax();
        int result = check.max(8, 5, 4);
        assertThat(result, is(8));
    }

    /**
     * Test max.
     */
    @Test
    public void whenSecondMax() {
        MultiMax check = new MultiMax();
        int result = check.max(2, 5, 4);
        assertThat(result, is(5));
    }

    /**
     * Test max.
     */
    @Test
    public void whenThirdMax() {
        MultiMax check = new MultiMax();
        int result = check.max(3, 5, 9);
        assertThat(result, is(9));
    }

    /**
     * Test max.
     */
    @Test
    public void whenAllAreEqual() {
        MultiMax check = new MultiMax();
        int result = check.max(3, 3, 3);
        assertThat(result, is(3));
    }
}
