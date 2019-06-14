package ru.job4j.exam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 14/06/2019
 */

public class AnalizeTest {
    /**
     * Test diff.
     */
    @Test
    public void whenCompareTwoListsThenShowStats() {
        Analize analize = new Analize();
        Analize.User one = new Analize.User(1, "u1");
        Analize.User two = new Analize.User(2, "u2");
        Analize.User three = new Analize.User(3, "u3");
        Analize.User threeChanged = new Analize.User(3, "u3 ch");
        Analize.User four = new Analize.User(4, "u4");
        Analize.User five = new Analize.User(5, "u5");
        Analize.User fiveChanged = new Analize.User(5, "u5 ch");
        Analize.User six = new Analize.User(6, "u6");

        List<Analize.User> previous = Arrays.asList(one, two, three, four, five);
        List<Analize.User> current = Arrays.asList(threeChanged, fiveChanged, six);
        Analize.Info result = analize.diff(previous, current);
        assertThat(result.added, is(1));
        assertThat(result.deleted, is(3));
        assertThat(result.changed, is(2));
    }
}