package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

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
     * Поле содержит разделитель строк.
     */
    private final String ln = System.lineSeparator();

    /**
     * Поле содержит строки меню.
     */
    private final String menu = this.showMenu();

    private String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(ln);
        menu.append("Menu.");
        menu.append(ln);
        menu.append("0. Add new Item");
        menu.append(ln);
        menu.append("1. Show all Items");
        menu.append(ln);
        menu.append("2. Edit Item");
        menu.append(ln);
        menu.append("3. Delete Item");
        menu.append(ln);
        menu.append("4. Find Item by Id");
        menu.append(ln);
        menu.append("5. Find Items by name");
        menu.append(ln);
        menu.append("6. Exit Program");
        menu.append(ln);
        return menu.toString();
    }


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
     * Test find by ID.
     * Порядок действий: создание объекта типа Item, создание объекта с последовательностью
     * ответов пользователя, создание объекта StartUI и применение основного метода init().
     * Тест проверяет, что на консоль (в массив байтов) будет выведено меню, строка first line, Имя заявки, Описание заявки,
     * снова меню.
     */
    @Test
    public void whenUserFindByIDThenOutputNameAndDesc() {
        ITracker iTracker = new Tracker();
        Item first = iTracker.add(new Item("test name1", "test desc1", 123L));
        Input input = new StubInput(new String[]{"4", first.getId(), "6"});
        new StartUI(input, iTracker, output).init();
        String firstLine = "------------ Поиск заявок по id --------------";
        assertThat(this.output.toString(), is(String.format(
                "%s%s%sИмя заявки - test name1%sОписание заявки - test desc1%s%s",
                menu, firstLine, ln, ln, ln, menu)));
    }

    /**
     * Test find by name.
     */
    @Test
    public void whenUserFindByNameThenOutputItem() {
        ITracker iTracker = new Tracker();
        Item first = iTracker.add(new Item("test name1", "test desc1", 123L));
        Input input = new StubInput(new String[]{"5", first.getName(), "6"});
        StartUI start = new StartUI(input, iTracker, output);
        start.init();
        String firstLine = "------------ Поиск заявок по имени --------------";
        assertThat(this.output.toString(), is(String.format(
                "%s%s%sЗаявка с номером id - %s%s%s",
                menu, firstLine, ln, first.getId(), ln, menu)));
    }

    /**
     * Test find all.
     */
    @Test
    public void whenUserFindAllThenShowAllItemsNames() {
        ITracker iTracker = new Tracker();
        Item first = iTracker.add(new Item("test name1", "test desc1", 123L));
        Input input = new StubInput(new String[] {"1", "6"});
        StartUI start = new StartUI(input, iTracker, output);
        start.init();
        String firstLine = "------------ Показ существующих заявок --------------";
        assertThat(this.output.toString(), is(String.format(
                "%s%s%sЗаявка 1 - %s%s%s",
                menu, firstLine, ln, first.getName(), ln, menu)));
    }
}