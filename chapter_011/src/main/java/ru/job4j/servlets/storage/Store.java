package ru.job4j.servlets.storage;

import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;

import java.util.Collection;

/**
 * @version 1.0.
 * @since 08/08/2019.
 * @author Evgeniya Tsiurupa
 */

public interface Store {
    void add(User user) throws UserValidationException;
    boolean update(User user, int id) throws UserValidationException;
    boolean delete(int id);
    Collection<User> findAll();
    User findById(int id);
    User findByLogin(String login);
}
