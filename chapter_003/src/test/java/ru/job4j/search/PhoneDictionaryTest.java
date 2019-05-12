package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 12/05/2019
 */

public class PhoneDictionaryTest {
    /**
     * Test find.
     */
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Иван", "Петров", "545121", "Москва"));
        List<Person> persons = phones.find("ва");
        assertThat(persons.iterator().next().getSurname(), is("Петров"));
    }

    /**
     * Test find.
     */
    @Test
    public void whenFindBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Иван", "Петров", "545121", "Москва"));
        List<Person> persons = phones.find("ров");
        assertThat(persons.iterator().next().getSurname(), is("Петров"));
    }

    /**
     * Test find.
     */
    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Иван", "Петров", "545121", "Москва"));
        List<Person> persons = phones.find("21");
        assertThat(persons.iterator().next().getSurname(), is("Петров"));
    }

    /**
     * Test find.
     */
    @Test
    public void whenFindByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Иван", "Петров", "545121", "Москва"));
        List<Person> persons = phones.find("оскв");
        assertThat(persons.iterator().next().getSurname(), is("Петров"));
    }
}
