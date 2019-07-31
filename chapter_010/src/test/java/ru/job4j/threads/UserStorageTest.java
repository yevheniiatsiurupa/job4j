package ru.job4j.threads;


import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @version 1.0.
 * @since 31/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class UserStorageTest {
    @Test
    public void whenTransferThenCorrectAmount() throws InterruptedException {
        UserStorage storage = new UserStorage();
        User u1 = new User(1, 1000);
        User u2 = new User(2, 2000);
        storage.add(u1);
        storage.add(u2);
        Thread t1 = new UserStorageThread(storage, 1, 2, 300);
        Thread t2 = new UserStorageThread(storage, 1, 2, 500);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(u1.getAmount(), is(200));
    }
}