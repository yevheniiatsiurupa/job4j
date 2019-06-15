package ru.job4j.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 15/06/2019
 */

public class ScriptTest {
    private Map<Integer, List<Integer>> testMap;
    private Script script;

    @Before
    public void beforeTest() {
        List<Integer> one = Arrays.asList(2, 3);
        List<Integer> two = Arrays.asList(4);
        List<Integer> three = Arrays.asList(4, 5);
        List<Integer> four = new ArrayList<>();
        List<Integer> five = new ArrayList<>();
        testMap = new HashMap<>();
        testMap.put(1, one);
        testMap.put(2, two);
        testMap.put(3, three);
        testMap.put(4, four);
        testMap.put(5, five);
        script = new Script();
    }

    /**
     * Test load.
     */
    @Test
    public void whenInputScriptThenOutputList() {
        List<Integer> result = script.load(testMap, 1);
        List<Integer> expected = Arrays.asList(2, 3, 4, 5);
        assertThat(result, is(expected));
    }

    /**
     * Test load.
     */
    @Test
    public void whenInputTwoScriptThenOutputList() {
        List<Integer> result = script.load(testMap, 2);
        List<Integer> expected = Arrays.asList(4);
        assertThat(result, is(expected));
    }

    /**
     * Test load.
     */
    @Test
    public void whenInputThreeScriptThenOutputList() {
        List<Integer> result = script.load(testMap, 3);
        List<Integer> expected = Arrays.asList(4, 5);
        assertThat(result, is(expected));
    }

    /**
     * Test load.
     */
    @Test
    public void whenInputFourScriptThenOutputList() {
        List<Integer> result = script.load(testMap, 4);
        List<Integer> expected = new ArrayList<>();
        assertThat(result, is(expected));
    }
}