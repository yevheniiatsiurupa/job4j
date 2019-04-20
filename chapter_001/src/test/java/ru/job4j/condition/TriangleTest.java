package ru.job4j.condition;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 20/04/2019
 */
public class TriangleTest {
    /**
     * Test area.
     */
    @Test
    public void whenSetThreePointsThenTriangleArea() {
        Point x = new Point(0, 0);
        Point y = new Point (0, 2);
        Point z = new Point (2, 0);
        Triangle triangle = new Triangle(x, y, z);
        double result = triangle.area();
        double expected = 2D;
        assertThat(result, closeTo(expected, 0.1));
    }
}
