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

public class SquareTest {
    /**
     * Test calculate.
     */
    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expected = {1, 4, 9};
        assertThat(rst, is(expected));
    }

    /**
     * Test calculate.
     */
    @Test
    public void whenBound1Then1() {
        int bound = 1;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expected = {1};
        assertThat(rst, is(expected));
    }

    /**
     * Test calculate.
     */
    @Test
    public void whenBound5Then1491625() {
        int bound = 5;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expected = {1, 4, 9, 16, 25};
        assertThat(rst, is(expected));
    }
}
