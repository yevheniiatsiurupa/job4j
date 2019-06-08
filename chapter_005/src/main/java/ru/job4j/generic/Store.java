package ru.job4j.generic;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 08/06/2019
 */

public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
