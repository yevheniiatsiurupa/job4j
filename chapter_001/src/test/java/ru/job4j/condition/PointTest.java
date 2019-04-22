package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 20/04/2019
 */

public class PointTest {

    /**
     * Test distance.
      */
            @Test
        public void whenZeroAndTenThenTen() {
            Point first = new Point(0, 0);
            Point second = new Point(0, 10);
            double result = first.distance(second);
            assertThat(result, is(10D));
        }

    /**
     * Test info.
     */
    @Test
    public void whenShowInfo() {
        Point first = new Point(1, 1);
        first.info();
        Point second = new Point(2, 2);
        second.info();
    }
}
