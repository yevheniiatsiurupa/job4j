package ru.job4j.exam;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    /**
     * Test change.
     */
    @Test
    public void whenChangeIs25() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] rst = coffeeMachine.change(50, 25);
        int[] expected = new int[] {10, 10, 5};
        assertThat(rst, is(expected));
    }

    /**
     * Test change.
     */
    @Test
    public void whenChangeIs17() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] rst = coffeeMachine.change(50, 33);
        int[] expected = new int[] {10, 5, 2};
        assertThat(rst, is(expected));
    }

    /**
     * Test change.
     */
    @Test
    public void whenChangeIs0() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] rst = coffeeMachine.change(50, 50);
        int[] expected = new int[0];
        assertThat(rst, is(expected));
    }

    /**
     * Test change.
     */
    @Test
    public void whenChangeIs14() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] rst = coffeeMachine.change(50, 36);
        int[] expected = new int[] {10, 2, 2};
        assertThat(rst, is(expected));
    }
}
