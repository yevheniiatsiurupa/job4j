package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class ConvertList2ArrayTest {
    /**
     * Test toArray.
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expected = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expected));
    }

    /**
     * Test toArray.
     */
    @Test
    public void when7ElementsThen8() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 4);
        int[][] expected = new int[][] {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 0}
        };
        assertThat(result, is(expected));
    }

    /**
     * Test convert.
     */
    @Test
    public void when2And4ElementsThen6Elements() {
        ConvertList2Array list = new ConvertList2Array();
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5, 6);
        int[] first = new int[] {1, 2};
        int[] second = new int[] {3, 4, 5, 6};
        List<int[]> initial = List.of(first, second);
        List<Integer> expected = list.convert(initial);
        assertThat(result, is(expected));
    }
}
