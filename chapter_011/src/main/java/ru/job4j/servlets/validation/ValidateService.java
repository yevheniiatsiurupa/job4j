package ru.job4j.servlets.validation;

import ru.job4j.servlets.models.User;
import ru.job4j.servlets.storage.DbStore;
import ru.job4j.servlets.storage.Store;

import java.util.Collection;

/**
 * @version 1.0.
 * @since 08/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class ValidateService implements Validate {

    /**
     * Ссылка на объект MemoryStore, в котором находится хранилище пользователей.
     */
    private final Store logic = DbStore.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return Holder.INSTANCE;
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
        boolean result = this.logic.update(user, id);
        if (!result) {
            throw new UserValidationException("User is not found.");
        }

    }

    /**
     * Метод для удаления пользователя в хранилище.
     * Перед операцией выполняется проверка.
     * Если данные не проходят проверку - выбрасывается исключение.
     * @param id номер пользователя для удаления.
     */
    @Override
    public void delete(int id) throws UserValidationException {
        boolean result = this.logic.delete(id);
        if (!result) {
            throw new UserValidationException("User is not found.");
        }
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

    /**
     * Метод возвращает пользователя по логину.
     * Перед операцией выполняется проверка.
     * Если данные не проходят проверку - выбрасывается исключение.
     */
    @Override
    public User findByLogin(String login) throws UserValidationException {
        if (this.logic.findByLogin(login) == null) {
            throw new UserValidationException("User is not found.");
        }
        return this.logic.findByLogin(login);
    }

    private static final class Holder{
        private static final ValidateService INSTANCE = new ValidateService();
    }
}
