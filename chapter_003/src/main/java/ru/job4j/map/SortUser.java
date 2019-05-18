package ru.job4j.map;

import java.util.Comparator;
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

    /**
     * Метод осуществляет сортировку по длине имени объекта User.
     * @param list исходный список объектов User.
     * @return возвращает отсортированный список объектов User.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                Integer i1 = o1.getName().length();
                Integer i2 = o2.getName().length();
                return i1.compareTo(i2);
            }
        });
        return list;
    }

    /**
     * Метод осуществляет сортировку по имени, потом по возрасту.
     * @param list исходный список объектов User.
     * @return возвращает отсортированный список объектов User.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                final int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : o1.getAge().compareTo(o2.getAge());
            }
        });
        return list;
    }
}