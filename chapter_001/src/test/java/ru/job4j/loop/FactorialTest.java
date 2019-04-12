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
public class FactorialTest {
    /**
     * Test calc.
     */
    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        Factorial factorialobj = new Factorial();
        int result = factorialobj.calc(5);
        assertThat(result, is(120));
    }

    /**
     * Test calc.
     */
    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial factorialobj = new Factorial();
        int result = factorialobj.calc(0);
        assertThat(result, is(1));
    }
}
