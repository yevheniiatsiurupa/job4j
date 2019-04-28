package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    /**
     * Поле запоминает стандартный вывод в консоль.
     */
    private final PrintStream stdout = System.out;
    /**
     * Поле создает буфер для хранения вывода.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Поле содержит разделитель строк.
     */
    private final String ln = System.lineSeparator();


    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Test find by ID.
     */
    @Test
    public void whenUserFindByIDThenOutputNameAndDesc() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "test desc1", 123L));
        Input input = new StubInput(new String[]{first.getId()});
        StartUI start = new StartUI(input, tracker);
        start.findItemById();
        String firstLine = "------------ Поиск заявок по id --------------";
        assertThat(new String(this.out.toByteArray()), is(String.format("%s%sИмя заявки - test name1%sОписание заявки - test desc1%s", firstLine, ln, ln, ln)));
    }

    /**
     * Test find by name.
     */
    @Test
    public void whenUserFindByNameThenOutputItem() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "test desc1", 123L));
        Input input = new StubInput(new String[]{first.getName()});
        StartUI start = new StartUI(input, tracker);
        start.findItemByName();
        String firstLine = "------------ Поиск заявок по имени --------------";
        assertThat(new String(this.out.toByteArray()), is(String.format("%s%sЗаявка с номером id - %s%s", firstLine, ln, first.getId(), ln)));
    }

    /**
     * Test find all.
     */
    @Test
    public void whenUserFindAllThenShowAllItemsNames() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test name1", "test desc1", 123L));
        Input input = new StubInput(new String[0]);
        StartUI start = new StartUI(input, tracker);
        start.showAllItems();
        String firstLine = "------------ Показ существующих заявок --------------";
        assertThat(new String(this.out.toByteArray()), is(String.format("%s%sЗаявка 1 - %s%s", firstLine, ln, first.getName(), ln)));
    }
}