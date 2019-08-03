package ru.job4j.threads.monitor;

import net.jcip.annotations.ThreadSafe;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.job4j.list.ContainerInterface;
import ru.job4j.list.SimpleContainer;

import java.util.Iterator;

/**
 * @version 1.0.
 * @since 31/07/2019.
 * @author Evgeniya Tsiurupa
 */

@ThreadSafe
public class ThreadSafeList<E>  implements ContainerInterface<E> {
    /**
     * Поле хранит ссылку на обычный список SimpleContainer.
     */
    protected final SimpleContainer<E> list;

    public ThreadSafeList(SimpleContainer<E> list) {
        this.list = list;
    }

    /**
     * Метод делает копию списка для последующего обхода итератором в режиме fail-safe.
     * @param array ссылка на список.
     * @return возвращает копию списка.
     */
    private SimpleContainer<E> copy(SimpleContainer<E> array) {
        SimpleContainer<E> result = new SimpleContainer<>();
        for (E tmp : array) {
            result.add(tmp);
        }
        return result;
    }

    /**
     * Синхронизированный потокобезопасный итератор.
     * Делает обход по копии списка, до которой нет доступа.
     * Следовательно такой итератор не выбрасывает ConcurrentModificationException.
     * @return итератор.
     */
    @Override
    @NonNull
    public synchronized Iterator<E> iterator() {
        return copy(this.list).iterator();
    }

    /**
     * Метод для добавления элемента.
     */
    @Override
    public synchronized void add(E value) {
        this.list.add(value);
    }

    /**
     * Метод для получения элемента по индексу.
     */
    @Override
    public synchronized E get(int index) {
        return this.list.get(index);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeList<String> list = new ThreadSafeList<>(new SimpleContainer<>());
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        Thread t = new ListThread<>(list);
        t.start();
        Thread.sleep(5000);
        list.add("five");
        System.out.println(list.get(4));
    }
}
