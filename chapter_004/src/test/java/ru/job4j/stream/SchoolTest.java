package ru.job4j.stream;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.List;

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
        Student first = new Student(90);
        Student second = new Student(65);
        Student third = new Student(100);
        Student fourth = new Student(50);
        Student fifth = new Student(10);
        Student sixth = new Student(30);
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
        Student first = new Student(90);
        Student second = new Student(65);
        Student third = new Student(100);
        Student fourth = new Student(50);
        Student fifth = new Student(10);
        Student sixth = new Student(30);
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
        Student first = new Student(90);
        Student second = new Student(65);
        Student third = new Student(100);
        Student fourth = new Student(50);
        Student fifth = new Student(10);
        Student sixth = new Student(30);
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
}
