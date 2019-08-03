package ru.job4j.threads.nonblock;

/**
 * @version 1.0.
 * @since 02/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class OptimisticException extends RuntimeException {
    public OptimisticException(String msg) {
        super(msg);
    }
}
