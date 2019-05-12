package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class PhoneDictionary {
    /**
     * Поле хранит список номеров в телефонном справочнике.
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Метод добавляет объект типа Person в список справочника.
     * @param person объект типа Person.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Метод для поиска объектов типа Person в справочнике по ключу.
     * @param key ключ для поиска (может содержаться в любом поле объекта)
     * @return возвращает список объектов типа Person, которые удовлетворяют поиску по ключу.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getName().contains(key)
                || person.getSurname().contains(key)
                || person.getAddress().contains(key)
                || person.getPhone().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
