package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 16/04/2019
 */

public class MatrixTest {
    /**
     * Test multiple.
     */
    @Test
    public void when2by2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expected = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expected));
    }
}
