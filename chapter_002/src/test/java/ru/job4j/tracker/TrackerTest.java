package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    /**
     * Test add.
     * Порядок действий: создание заявки, добавление в заявки в трекер.
     * Тест сравнивает имя заявки, найденной в трекере по id и созданной.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        ITracker iTracker = new Tracker();
        long created = System.currentTimeMillis();
        Item item = new Item("test1", "testDescription", created);
        iTracker.add(item);
        Item result = iTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    /**
     * Test replace.
     * Порядок действий: создание заявки, добавление в заявки в трекер,
     * создание новой заявки, копия id из старой заявки в новую,
     * замена заявки в хранилище.
     * Тест сравнивает имя заявки, найденной в трекере по id и созданной новой заявки.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        ITracker iTracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        iTracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        iTracker.replace(previous.getId(), next);
        assertThat(iTracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Test delete.
     * Порядок действий: создание трех заявок, добавление заявок в трекер,
     * удаление первой заявки.
     * Тест сравнивает полученный после удаления массив заявок и ожидаемый массив.
     */
    @Test
    public void whenDeleteNameThenReturnNewArray() {
        ITracker iTracker = new Tracker();
        Item first = new Item("test1", "testDescription1", 123L);
        Item second = new Item("test2", "testDescription2", 1234L);
        Item third = new Item("test3", "testDescription3", 1235L);
        iTracker.add(first);
        iTracker.add(second);
        iTracker.add(third);
        iTracker.delete(first.getId());
        List<Item> expected = new ArrayList<>(Arrays.asList(second, third));
        assertThat(iTracker.findAll(), is(expected));
    }

    /**
     * Test findAll.
     * Порядок действий: создание трех заявок, добавление заявок в трекер.
     * Тест сравнивает массив, возращаемый методом findAll и ожидаемый.
     */
    @Test
    public void whenFindAllThenReturnRange() {
        ITracker iTracker = new Tracker();
        Item first = new Item("test1", "testDesc1", 123L);
        Item second = new Item("test2", "testDesc2", 1234L);
        Item third = new Item("test3", "testDesc3", 1235L);
        iTracker.add(first);
        iTracker.add(second);
        iTracker.add(third);
        List<Item> result = iTracker.findAll();
        List<Item> expected = new ArrayList<>(Arrays.asList(first, second, third));
        assertThat(result, is(expected));
    }

    /**
     * Test findByName.
     * Порядок действий: создание трех заявок, добавление заявок в трекер.
     * Тест сравнивает массив, возращаемый методом findByName и ожидаемый.
     */
    @Test
    public void whenFindByNameThenReturnTwoItems() {
        ITracker iTracker = new Tracker();
        Item first = new Item("test1", "testDesc1", 123L);
        Item second = new Item("test2", "testDesc2", 1234L);
        Item third = new Item("test1", "testDesc3", 1235L);
        iTracker.add(first);
        iTracker.add(second);
        iTracker.add(third);
        List<Item> result = iTracker.findByName("test1");
        List<Item> expected = new ArrayList<>(Arrays.asList(first, third));
        assertThat(result, is(expected));
    }

    /**
     * Test findById.
     * Порядок действий: создание заявки, добавление в заявки в трекер.
     * Тест сравнивает имя заявки, найденной в трекере по id и созданной  заявки.
     */
    @Test
    public void whenFindByIdThenReturnElement() {
        ITracker iTracker = new Tracker();
        Item first = new Item("test1", "testDesc1", 12L);
        iTracker.add(first);
        Item result = iTracker.findById(first.getId());
        assertThat(result.getName(), is("test1"));
    }
}
