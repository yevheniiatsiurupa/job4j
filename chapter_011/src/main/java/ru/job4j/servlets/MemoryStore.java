package ru.job4j.servlets;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0.
 * @since 08/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();

    /**
     * Хранилище пользователей.
     */
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * Поле хранит следующий номер для присвоения следующему пользователю,
     * который будет добавлен в хранилище.
     */
    private final AtomicInteger nextId = new AtomicInteger(0);

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для добавления пользователя в хранилище.
     * Пользователю добавляется уникальный номер.
     * @param user пользователь для добавления.
     */
    @Override
    public void add(User user) {
        user.setId(this.nextId.getAndIncrement());
        users.put(user.getId(), user);
    }

    /**
     * Метод для удаления пользователя по номеру.
     * @param id номер пользователя.
     */
    @Override
    public void delete(int id) {
        this.users.remove(id, this.findById(id));
    }

    /**
     * Метод для обновления пользователя в хранилище.
     * @param user пользователь, который будет внесет в хранилище вместо редактируемого.
     * @param id номер редактируемого пользователя.
     */
    @Override
    public void update(User user, int id) {
        this.users.put(id, user);
    }

    /**
     * Метод для нахождения пользователя по id.
     * @param id номер id пользователя.
     * @return возвращает пользователя с id.
     */
    @Override
    public User findById(int id) {
        return this.users.get(id);
    }

    /**
     * Метод возвращает коллекцию всех пользователей.
     */
    @Override
    public Collection<User> findAll() {
        return this.users.values();
    }
}
