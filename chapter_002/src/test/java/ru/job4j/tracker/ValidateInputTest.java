package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Evgeniya Tsiurupa.
 * @version 1.0.
 * @since 01/05/2019.
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Test ask (validate input).
     * Создаем объект ввода данных input.
     * В конструктор ValidateInput передаем объект StubInput.
     * В конструктор StubInput передаем последовательность ответов пользователя.
     * Пользователь должен ввести неверное значение invalid, программа выбрасывает исключение, пользователь
     * вводит новое значение, которое находится в массиве допустимых значений range.
     */
    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"invalid", "1"}));
        List<Integer> range = new ArrayList<>();
        range.add(1);
        input.ask("Enter", range);
        assertThat(new String(this.out.toByteArray()), is(String.format("Please enter valid data again.%n")));
    }

    /**
     * Test ask (validate input).
     */
    @Test
    public void whenOutOfMenuInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"-1", "1"}));
        List<Integer> range = new ArrayList<>();
        range.add(1);
        input.ask("Enter", range);
        assertThat(new String(this.out.toByteArray()), is(String.format("Please select key from menu.%n")));
    }
}
