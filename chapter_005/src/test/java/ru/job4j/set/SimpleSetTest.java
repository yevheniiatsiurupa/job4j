package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 10/06/2019
 */

public class SimpleSetTest {
    /**
     * Test SimpleSet add / size.
     */
    @Test
    public void whenPutTheSameElementThenHasOneElement() {
        SimpleSet<String> testSet = new SimpleSet<>();
        testSet.add("001");
        testSet.add("002");
        testSet.add("001");

        assertThat(testSet.size(), is(2));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIterateThenShowAllElements() {
        SimpleSet<String> testSet = new SimpleSet<>();
        testSet.add("001");
        testSet.add("002");
        testSet.add("001");
        testSet.add("003");

        Iterator it = testSet.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Test SimpleSet add / size.
     */
    @Test
    public void whenPutTheSameElementAndNullThenHasOneElement() {
        SimpleSet<Integer> testSet = new SimpleSet<>();
        testSet.add(1);
        testSet.add(null);
        testSet.add(1);
        testSet.add(5);
        Iterator it = testSet.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}