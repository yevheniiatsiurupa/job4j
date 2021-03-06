package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Метод возвращает список студентов, у которых средняя оценка выше bound.
     * @param students исходный список студентов.
     * @param bound предел средней оценки.
     * @return возвращает новый список.
     */
    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        Integer first = s1.getScore();
                        Integer second = s2.getScore();
                        return second.compareTo(first);
                    }
                })
                .flatMap(Stream::ofNullable)
                .takeWhile(stud -> stud.getScore() > bound)
                .collect(Collectors.toList());
    }
}
