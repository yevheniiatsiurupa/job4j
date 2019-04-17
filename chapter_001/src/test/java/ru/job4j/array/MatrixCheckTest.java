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

public class MatrixCheckTest {
    /**
     * Test mono.
     */
    @Test
    public void whenDataMonoByTrueThenTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, true},
                {false, true, false},
                {true, false, true}
        };
        boolean result = matrixCheck.mono(input);
        boolean expected = true;
        assertThat(result, is(expected));
    }

    /**
     * Test mono.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, true},
                {false, false, false},
                {true, false, true}
        };
        boolean result = matrixCheck.mono(input);
        boolean expected = false;
        assertThat(result, is(expected));
    }

    /**
     * Test mono.
     */
    @Test
    public void whenData2by2MonoByTrueThenTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, false},
                {false, true}
        };
        boolean result = matrixCheck.mono(input);
        boolean expected = true;
        assertThat(result, is(expected));
    }

    /**
     * Test mono.
     */
    @Test
    public void whenNotData2by2MonoByTrueThenFalse() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, false},
                {false, false}
        };
        boolean result = matrixCheck.mono(input);
        boolean expected = false;
        assertThat(result, is(expected));
    }
}
