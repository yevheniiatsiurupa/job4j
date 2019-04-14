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

public class TurnTest {
    /**
     * Test back.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfTheElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {1, 2, 3, 4};
        int[] result = turner.back(input);
        int[] expected = new int[] {4, 3, 2, 1};
        assertThat(result, is(expected));
    }

    /**
     * Test back.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfTheElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {1, 2, 3, 4, 5};
        int[] result = turner.back(input);
        int[] expected = new int[] {5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
}
