package ru.job4j.cinema.validation;

import ru.job4j.cinema.models.Account;
import ru.job4j.cinema.models.HallPlace;
import ru.job4j.cinema.storage.DbStore;

import java.util.Collection;

public class ValidateService {

    /**
     * Ссылка на объект MemoryStore, в котором находится хранилище пользователей.
     */
    private final DbStore logic = DbStore.getInstance();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return Holder.INSTANCE;
    }

    public void bookSeat(String seatId, Account account) throws Exception {
        this.logic.bookSeat(seatId, account);
    }

    public Collection<HallPlace> findAll() {
        Collection<HallPlace> result = this.logic.findAll();
        return result;
    }

    private static final class Holder {
        private static final ValidateService INSTANCE = new ValidateService();
    }
}
