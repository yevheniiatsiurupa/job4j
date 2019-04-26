package ru.job4j.paint;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/04/2019
 */

public class TriangleTest {
    /**
     * Test draw.
     */
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        String ln = System.lineSeparator();
        assertThat(triangle.draw(),is(new StringBuilder()
                .append("   +   ")
                .append(ln)
                .append("  +++  ")
                .append(ln)
                .append(" +++++ ")
                .append(ln)
                .append("+++++++")
                .toString()));
    }
}
