package ru.job4j.threads.nonblock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class NonBlockCacheTest {
    Base first = new Base(1);
    NonBlockCache storage = new NonBlockCache(
            new ConcurrentHashMap<>()
    );

    @Test
    public void whenAddElementThenGetId() {

        storage.add(first);
        assertThat(storage.get(1), is(first));
    }

    @Test
    public void whenDeleteThenNotFound() {
        storage.add(first);
        storage.delete(first);
        assertNull(storage.get(1));
    }

    @Test
    public void whenUpdateThenNewVersion() throws InterruptedException {
        storage.add(first);
        Thread t = new ChangeUpdateThread(storage, first);
        t.start();
        t.join();
        assertThat(storage.get(1).getVersion(), is(1));
    }

    @Test
    public void whenUpdateWithTwoThreadsThenException() {
        AtomicReference<Exception> ex = new AtomicReference<>();
        storage.add(first);
        first.setVersion(1);
        Base change = new Base(1);
        Thread t1 = new ChangeUpdateThread(storage, change);
        try {
            t1.start();
            t1.join();
        } catch (Exception e) {
            ex.set(e);
            Assert.assertThat(ex.get().getMessage(), is("Object was modified."));
        }
    }
}