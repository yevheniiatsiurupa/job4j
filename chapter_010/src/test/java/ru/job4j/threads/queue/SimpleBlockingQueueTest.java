package ru.job4j.threads.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0.
 * @since 01/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class SimpleBlockingQueueTest {
    /**
     * Поле запоминает стандартный вывод в консоль.
     */
    private final PrintStream stdout = System.out;
    /**
     * Поле создает буфер для хранения вывода.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Поле для организации вывода.
     */
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };

    /**
     * Метод заменяет стандартный вывод System.out на вывод в созданный массив байтов out.
     */

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Метод возвращает стандартный вывод System.out.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Поле содержит разделитель строк.
     */
    private final String ln = System.lineSeparator();

    @Test
    public void whenNoElementThenConsumerWaits() throws InterruptedException {
        SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>(2);
        Thread producer = new ProducerThread<>(queue, "first");
        Thread consumer = new ConsumerThread<>(queue);
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();

        StringBuilder sb = new StringBuilder();
        sb.append("Waiting for producer.");
        sb.append(ln);
        sb.append("Added element: first");
        sb.append(ln);
        sb.append("Polled element: first");
        sb.append(ln);

        assertThat(this.output.toString(), is(sb.toString()));
    }

    @Test
    public void whenMaxSizeThenProducerWaits() throws InterruptedException {
        SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>(1);
        Thread producer1 = new ProducerThread<>(queue, "first");
        Thread producer2 = new ProducerThread<>(queue, "second");
        Thread consumer = new ConsumerThread<>(queue);

        producer1.start();
        producer2.start();
        producer1.join();
        consumer.start();
        producer2.join();
        consumer.join();

        StringBuilder sb = new StringBuilder();
        sb.append("Added element: first");
        sb.append(ln);
        sb.append("Waiting for consumer.");
        sb.append(ln);
        sb.append("Polled element: first");
        sb.append(ln);
        sb.append("Added element: second");
        sb.append(ln);

        assertThat(this.output.toString(), is(sb.toString()));
    }
}