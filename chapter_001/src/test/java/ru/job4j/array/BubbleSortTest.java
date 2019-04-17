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

public class BubbleSortTest {
    /**
     * Test sort.
     */
    @Test
    public void whenSortArrayTenElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[] {1, 5, 4, 2, 3, 9, 7, 8, 6, 10};
        int[] result = bubbleSort.sort(input);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result, is(expected));
    }

}
