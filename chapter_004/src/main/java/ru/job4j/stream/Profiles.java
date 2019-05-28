package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 28/05/2019
 */

public class Profiles {
    /**
     * Метод составляет список адресов по списку клиентов.
     * Список хранит только уникальные элементы, отсортированные по имени города.
     * @param profiles список клиентов.
     * @return возвращает список адресов.
     */
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .distinct()
                .sorted(new Comparator<Address>() {
                    @Override
                    public int compare(Address o1, Address o2) {
                        return o1.getCity().compareTo(o2.getCity());
                    }
                })
                .collect(Collectors.toList());
    }
}
