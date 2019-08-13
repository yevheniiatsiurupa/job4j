package ru.job4j.servlets.validation;

import ru.job4j.servlets.User;

import java.util.Collection;

/**
 * @version 1.0.
 * @since 09/08/2019.
 * @author Evgeniya Tsiurupa
 */

public interface Validate {
    void add(User user) throws UserValidationException;
    void update(User user, int id) throws UserValidationException;
    void delete(int id) throws UserValidationException;
    Collection<User> findAll() throws UserValidationException;
    User findById(int id) throws UserValidationException;
}
