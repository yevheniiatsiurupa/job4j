package ru.job4j.servlets;

import java.util.Collection;

/**
 * @version 1.0.
 * @since 08/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class ValidateService implements Validate {
    private static final ValidateService INSTANCE = new ValidateService();

    /**
     * Ссылка на объект MemoryStore, в котором находится хранилище пользователей.
     */
    private final Store logic = MemoryStore.getInstance();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для добавления пользователя в хранилище.
     * Перед операцией выполняется проверка.
     * Если данные не проходят проверку - выбрасывается исключение.
     * @param user пользователь для добавления
     */
    @Override
    public void add(User user) throws UserValidationException {
        if (user.getName() == null) {
            throw new UserValidationException("User name is null.");
        }
        this.logic.add(user);
    }

    /**
     * Метод для редактирования пользователя в хранилище.
     * Перед операцией выполняется проверка.
     * Если данные не проходят проверку - выбрасывается исключение.
     * @param user пользователь, на которого будет заменен пользователь в хранилище.
     * @param id номер пользователя для редактирования.
     */
    @Override
    public void update(User user, int id) throws UserValidationException {
        if (this.logic.findById(id) == null) {
            throw new UserValidationException("User is not found.");
        }
        this.logic.update(user, id);
    }

    /**
     * Метод для удаления пользователя в хранилище.
     * Перед операцией выполняется проверка.
     * Если данные не проходят проверку - выбрасывается исключение.
     * @param id номер пользователя для удаления.
     */
    @Override
    public void delete(int id) throws UserValidationException {
        if (this.logic.findById(id) == null) {
            throw new UserValidationException("User is not found.");
        }
        this.logic.delete(id);
    }

    /**
     * Метод возвращает список всех пользователей.
     * Перед операцией выполняется проверка.
     * Если данные не проходят проверку - выбрасывается исключение.
     */
    @Override
    public Collection<User> findAll() throws UserValidationException {
        Collection<User> result = this.logic.findAll();
        if (result.size() == 0) {
            throw new UserValidationException("Users are not found.");
        }
        return result;
    }

    /**
     * Метод возвращает пользователя по номеру.
     * Перед операцией выполняется проверка.
     * Если данные не проходят проверку - выбрасывается исключение.
     * @param id номер искомого пользователя.
     */
    @Override
    public User findById(int id) throws UserValidationException {
        if (this.logic.findById(id) == null) {
            throw new UserValidationException("User is not found.");
        }
        return this.logic.findById(id);
    }
}
