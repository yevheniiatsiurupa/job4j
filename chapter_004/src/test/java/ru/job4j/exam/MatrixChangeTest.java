package ru.job4j.exam;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 07/06/2019
 */

public class MatrixChangeTest {
    /**
     * Test changeMatrix.
     */
    @Test
    public void whenInputMatrixThenReturnResultingCalculation() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        MatrixChange test = new MatrixChange();
        int result = test.changeMatrix(input);
        int expected = 120;
        assertThat(result, is(expected));
    }

}