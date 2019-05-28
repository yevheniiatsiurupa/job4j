package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 28/05/2019
 */

public class School {
    /**
     * Метод для фильтрации учеников на отдельные классы.
     * @param students исходный список учеников.
     * @param predicate условие для фильтрации.
     * @return возвращает отфильтрованный список.
     */
    List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Метод создает карту с ключом - фамилия ученика, значением - объект ученика.
     * @param students исходный список учеников.
     * @return возвращает карту фамилия - ученик.
     */
    Map<String, Student>  convertToMap(List<Student> students) {
        return students.stream().collect(Collectors.toMap(
                Student :: getSurname,
                student -> student
        ));
    }
}
