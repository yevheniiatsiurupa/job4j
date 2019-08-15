package ru.job4j.servlets.validation;

import ru.job4j.servlets.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    }

    @Override
    public void delete(int id) throws UserValidationException {

    }

    @Override
    public Collection<User> findAll() throws UserValidationException {
        return this.store.values();
    }

    @Override
    public User findById(int id) throws UserValidationException {
        return null;
    }

    @Override
    public User findByLogin(String login) throws UserValidationException {
        return null;
    }
}
