package ru.job4j.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/05/2019
 */

public class MyFunctionTest {
    /**
     * Test diapason (for linear function).
     */
    @Test
    public void whenLinearFunctionThenLinearResults() {
        MyFunction function = new MyFunction();
        List<Double> result = function.diapason(5, 7, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    /**
     * Test diapason (for square function).
     */
    @Test
    public void whenSquareFunctionThenSquareResults() {
        MyFunction function = new MyFunction();
        List<Double> result = function.diapason(1, 3, x -> Math.pow(x, 2) + 1);
        List<Double> expected = Arrays.asList(2D, 5D, 10D);
        assertThat(result, is(expected));
    }

    /**
     * Test diapason (for logarithmic function).
     */
    @Test
    public void whenLogFunctionThenLogResults() {
        MyFunction function = new MyFunction();
        List<Double> result = function.diapason(10, 12, x -> Math.log10(x) + 1);
        List<Double> expected = Arrays.asList(2D, Math.log10(11) + 1, Math.log10(12) + 1);
        assertThat(result, is(expected));
    }
}
