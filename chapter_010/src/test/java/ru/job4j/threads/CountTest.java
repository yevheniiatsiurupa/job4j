package ru.job4j.threads;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0.
 * @since 31/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class CountTest {
    /**
     * Класс описывает нить со счетчиком.
     */
    private class ThreadCount extends Thread {
        private final Count count;

        public ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws Exception {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}