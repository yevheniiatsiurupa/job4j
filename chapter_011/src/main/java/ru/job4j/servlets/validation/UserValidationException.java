package ru.job4j.servlets.validation;

/**
 * @version 1.0.
 * @since 09/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class UserValidationException extends Exception {
    public UserValidationException(String msg) {
        super(msg);
    }
}
