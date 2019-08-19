package ru.job4j.servlets.storage;


import ru.job4j.servlets.models.UserJson;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryStorageJson {
    private static final MemoryStorageJson INSTANCE = new MemoryStorageJson();

    /**
     * Хранилище пользователей.
     */
    private final Map<Integer, UserJson> users = new ConcurrentHashMap<>();

    /**
     * Поле хранит следующий номер для присвоения следующему пользователю,
     * который будет добавлен в хранилище.
     */
    private final AtomicInteger nextId = new AtomicInteger(0);

    private MemoryStorageJson() {
    }

    public static MemoryStorageJson getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для добавления пользователя в хранилище.
     * Пользователю добавляется уникальный номер.
     * @param user пользователь для добавления.
     */
    public void add(UserJson user) {
        user.setId(this.nextId.getAndIncrement());
        users.put(user.getId(), user);
    }
}
