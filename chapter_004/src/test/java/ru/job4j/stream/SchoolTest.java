package ru.job4j.stream;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import java.util.Map;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 27/05/2019
 */

public class SchoolTest {
    /**
     * Test collect (class A).
     */
    @Test
    public void whenRangeAThenClassA() {
        Student first = new Student(90, "Petrov");
        Student second = new Student(65, "Petrov");
        Student third = new Student(100, "Petrov");
        Student fourth = new Student(50, "Petrov");
        Student fifth = new Student(10, "Petrov");
        Student sixth = new Student(30, "Petrov");
        List<Student> students = List.of(
                first, second, third,
                fourth, fifth, sixth
        );
        List<Student> expected = List.of(first, third);
        School testSchool = new School();
        List<Student> result = testSchool.collect(students,
                student -> student.getScore() >= 70 && student.getScore() <= 100);
        assertThat(result, is(expected));
    }

    /**
     * Test collect (class B).
     */
    @Test
    public void whenRangeBThenClassB() {
        Student first = new Student(90, "Petrov");
        Student second = new Student(65, "Petrov");
        Student third = new Student(100, "Petrov");
        Student fourth = new Student(50, "Petrov");
        Student fifth = new Student(10, "Petrov");
        Student sixth = new Student(30, "Petrov");
        List<Student> students = List.of(
                first, second, third,
                fourth, fifth, sixth
        );
        List<Student> expected = List.of(second, fourth);
        School testSchool = new School();
        List<Student> result = testSchool.collect(students,
                student -> student.getScore() >= 50 && student.getScore() < 70);
        assertThat(result, is(expected));
    }

    /**
     * Test collect (class C).
     */
    @Test
    public void whenRangeCThenClassC() {
        Student first = new Student(90, "Petrov");
        Student second = new Student(65, "Petrov");
        Student third = new Student(100, "Petrov");
        Student fourth = new Student(50, "Petrov");
        Student fifth = new Student(10, "Petrov");
        Student sixth = new Student(30, "Petrov");
        List<Student> students = List.of(
                first, second, third,
                fourth, fifth, sixth
        );
        List<Student> expected = List.of(fifth, sixth);
        School testSchool = new School();
        List<Student> result = testSchool.collect(students,
                student -> student.getScore() > 0 && student.getScore() < 50);
        assertThat(result, is(expected));
    }

    /**
     * Test convertToMap.
     */
    @Test
    public void whenHaveStudentsThenReturnMap() {
        Student first = new Student(100, "Ivanov");
        Student second = new Student(100, "Petrov");
        Student third = new Student(100, "Smith");
        List<Student> students = List.of(first, second, third);
        School testSchool = new School();
        Map<String, Student> result = testSchool.convertToMap(students);
        Map<String, Student> expected = Map.of("Ivanov", first, "Petrov", second, "Smith", third);
        assertThat(result, is(expected));
    }
}
