package ru.job4j.list;


public interface ContainerInterface<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
