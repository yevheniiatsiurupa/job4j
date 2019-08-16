package ru.job4j.servlets.validation;

import ru.job4j.servlets.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс используется в тестах вместо ValidateService.
 * @version 1.0.
 * @since 16/08/2019.
 * @author Evgeniya Tsiurupa
 */
public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public void add(User user) throws UserValidationException {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
    }

    @Override
    public void update(User user, int id) throws UserValidationException {
        user.setId(id);
        this.store.put(id, user);
    }

    @Override
    public void delete(int id) throws UserValidationException {
        this.store.remove(id, this.findById(id));
    }

    @Override
    public Collection<User> findAll() throws UserValidationException {
        return this.store.values();
    }

    @Override
    public User findById(int id) throws UserValidationException {
        return this.store.get(id);
    }

    @Override
    public User findByLogin(String login) throws UserValidationException {
        return null;
    }
}
