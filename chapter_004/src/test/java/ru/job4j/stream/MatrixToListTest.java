package ru.job4j.stream;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.List;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 28/05/2019
 */

public class MatrixToListTest {
    /**
     * Test convertMatrix.
     */
    @Test
    public void whenInputMatrixThenReturnList() {
        Integer[][] input = new Integer[][] {{1, 2}, {3, 4}};
        MatrixToList temp = new MatrixToList();
        List<Integer> result = temp.convertMatrix(input);
        List<Integer> expected = List.of(1, 2, 3, 4);
        assertThat(result, is(expected));
    }
}
