package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 04/04/2019
 */
public class CalculateTest {
    /**
     * Test echo.
     */

    @Test
    public void whenTakeNameThenThreeEchoPlusName() {
        String input = "Evgeniya";
        String expect = "Echo, echo, echo : Evgeniya";
        Calculate calc = new Calculate();
        String result = calc.echo(input);
        assertThat(result, is(expect));
    }

}