package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 27/05/2019
 */

public class Profiles {
    /**
     * Метод составляет список адресов по списку клиентов.
     * @param profiles список клиентов.
     * @return возвращает список адресов.
     */
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }
}
