package ru.job4j.exam;

import org.junit.Test;
import ru.job4j.threads.pool.email.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0.
 * @since 07/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class EmailProducerTest {
    @Test
    public void whenEmailThenSend() throws InterruptedException {
        List<User> list = new ArrayList<>();
        list.add(new User("user 1", "email-1"));
        list.add(new User("user 2", "email-2"));
        list.add(new User("user 3", "email-3"));

        EmailProducer emailProducer = new EmailProducer(list, 5000L);
        emailProducer.start();
        Thread.sleep(8000);
        emailProducer.interrupt();
        assertThat(emailProducer.isInterrupted(), is(true));
    }
}