package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {
    /**
     * Test add Item.
     * Создаем объект Tracker, StubInput, в который передаем массив
     * из последовательности выбора действий виртуального пользователя.
     * Создаем объект StartUI с параметрами input, tracker.
     * Вызываем метод init для созданного объекта StartUI.
     * Проверяем, что заявка успешно добавилась в трекер.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        ITracker iTracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "test name", "test desc", "6"});
        new StartUI(input, iTracker, System.out::println).init();
        assertThat(iTracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Test edit Item.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        ITracker iTracker = new Tracker();
        Item item = iTracker.add(new Item("test name", "test desc", 123L));
        Input input = new StubInput(new String[] {"2", item.getId(), "test replace", "test desc new", "6"});
        new StartUI(input, iTracker, System.out::println).init();
        assertThat(iTracker.findAll().get(0).getName(), is("test replace"));
    }

    /**
     * Test delete Item.
     */
    @Test
    public void whenDeleteThenTrackerHasOneElement() {
        ITracker iTracker = new Tracker();
        Item one = iTracker.add(new Item("test name", "test desc", 123L));
        Item two = iTracker.add(new Item("test name2", "test desc2", 1234L));
        Input input = new StubInput(new String[] {"3", two.getId(), "6"});
        new StartUI(input, iTracker, System.out::println).init();
        List<Item> result = new ArrayList<>();
        result.add(one);
        assertThat(result, is(iTracker.findAll()));
    }
}
