package ru.job4j.paint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/04/2019
 */

public class PaintTest {
    /**
     *Поле содержит стандартный вывод в консоль.
     */
    private final PrintStream stdout = System.out;
    /**
     * Поле содержит буфер для хранения вывода.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Метод для замены стандартного вывода.
     */
    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Метод для возврата стандартного вывода.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }


    /**
     * Test draw.
     */
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("++++")
                                .append(System.lineSeparator())
                                .append("+  +")
                                .append(System.lineSeparator())
                                .append("+  +")
                                .append(System.lineSeparator())
                                .append("++++")
                                .append(System.lineSeparator())
                                .toString()
                ));
    }

    /**
     * Test draw.
     */
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   +   ")
                                .append(System.lineSeparator())
                                .append("  +++  ")
                                .append(System.lineSeparator())
                                .append(" +++++ ")
                                .append(System.lineSeparator())
                                .append("+++++++")
                                .append(System.lineSeparator())
                                .toString()
                ));
    }
}
