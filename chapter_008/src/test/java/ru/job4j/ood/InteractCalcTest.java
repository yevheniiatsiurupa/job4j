package ru.job4j.ood;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.calculator.Calculator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 29/06/2019
 */



public class InteractCalcTest {
    private String ln = System.lineSeparator();

    /**
     * Поле запоминает стандартный вывод в консоль.
     */
    private final PrintStream stdout = System.out;
    /**
     * Поле создает буфер для хранения вывода.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Поле для организации вывода.
     */
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };


    /**
     * Метод заменяет стандартный вывод System.out на вывод в созданный массив байтов out.
     */

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Метод возвращает стандартный вывод System.out.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Test InteractCalc.
     */
    @Test
    public void whenAddTwoNumbersThenSum() {
        Input input = new StubInput(new String[]{"+", "1", "2", "q"});
        String result = "Result: 3.0" + ln;
        new InteractCalc(new Calculator(), input).init();
        assertThat(this.output.toString(), is(result));
    }

    /**
     * Test InteractCalc.
     */
    @Test
    public void whenAddThreeNumbersThenSum() {
        Input input = new StubInput(new String[]{"+", "1", "2", "c", "+", "5", "q"});
        String result = String.format("%s%s%s%s", "Result: 3.0", ln, "Result: 8.0", ln);
        new InteractCalc(new Calculator(), input).init();
        assertThat(this.output.toString(), is(result));
    }
}