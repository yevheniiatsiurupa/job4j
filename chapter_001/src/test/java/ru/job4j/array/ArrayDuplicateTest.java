package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 16/04/2019
 */

public class ArrayDuplicateTest {
    /**
     * Test remove.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"Привет", "Пока", "Пока", "Привет", "Супер"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = {"Привет", "Супер", "Пока"};
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenRemoveDuplicates2ThenArrayWithoutDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"1", "1", "1", "1"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = {"1"};
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenRemoveDuplicates3ThenArrayWithoutDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = {"Привет", "Мир", "Супер"};
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenRemoveDuplicates4ThenArrayWithoutDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Супер"};
        String[] result = arrayDuplicate.remove(input);
        String[] expected = {"Привет", "Мир", "Супер"};
        assertThat(result, arrayContainingInAnyOrder(expected));
    }
}
