package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 09/06/2019
 */

public class LoopSearchTest {
    /**
     * Test hasCycle.
     */
    @Test
    public void whenHasLoopThenTrue() {
        LoopSearch.Node<Integer> first = new LoopSearch.Node<>(1);
        LoopSearch.Node<Integer> two = new LoopSearch.Node<>(2);
        LoopSearch.Node<Integer> third = new LoopSearch.Node<>(3);
        LoopSearch.Node<Integer> four = new LoopSearch.Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        LoopSearch<Integer> testLoop = new LoopSearch<>();
        assertThat(testLoop.hasCycle(first), is(true));
    }

    /**
     * Test hasCycle.
     */
    @Test
    public void whenDoesNotHaveLoopThenFalse() {
        LoopSearch.Node<Integer> first = new LoopSearch.Node<>(1);
        LoopSearch.Node<Integer> two = new LoopSearch.Node<>(2);
        LoopSearch.Node<Integer> third = new LoopSearch.Node<>(3);
        LoopSearch.Node<Integer> four = new LoopSearch.Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;

        LoopSearch<Integer> testLoop = new LoopSearch<>();
        assertThat(testLoop.hasCycle(first), is(false));
    }

    /**
     * Test hasCycle.
     */
    @Test
    public void whenNoElementsThenFalse() {
        LoopSearch.Node<Integer> first = null;
        LoopSearch<Integer> testLoop = new LoopSearch<>();
        assertThat(testLoop.hasCycle(first), is(false));
    }
}