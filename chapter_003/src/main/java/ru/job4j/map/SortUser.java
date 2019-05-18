package ru.job4j.map;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 18/05/2019
 */

public class SortUser {
    /**
     * Метод преобразует список пользователей в отсортированное множество типа TreeSet.
     * @param list исходный список.
     * @return возвращает отсортированный список.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}