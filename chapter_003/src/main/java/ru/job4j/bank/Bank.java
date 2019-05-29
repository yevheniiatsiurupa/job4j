package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Evgeniya Tsiurupa
 * @version 2.0
 * @since 20/05/2019
 */

public class Bank {
    /**
     * Поле содержит хранилище всех счетов отдельных клиентов.
     */
    private Map<User, List<Account>> database = new TreeMap<>();

    /**
     * Метод для поиска пользователя по номеру паспорта.
     * @param passport номер паспорта.
     * @return возвращает пользователя.
     */
    public User findUserByPassport(String passport) {
        Set<User> tmp = database.keySet();
        return tmp.stream().filter(user -> user.getPassport().equals(passport)).findFirst().orElse(null);
    }

    /**
     * Метод для поиска счета по номеру паспорта пользователя и реквизитам.
     * @param passport паспорт пользователя.
     * @param requisite реквизиты.
     * @return возвращает счет.
     */
    public Account findAccount(String passport, String requisite) {
        User o1 = this.findUserByPassport(passport);
        List<Account> a1 = database.get(o1);
        return a1.stream().filter(tmp -> tmp.getRequisites().equals(requisite)).findFirst().orElse(null);
    }

    /**
     * Метод добавляет нового пользователя и список его счетов.
     * @param user добавляемый пользователь.
     */
    public void addUser(User user) {
        this.database.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя.
     * @param user удаляемый пользователь.
     */
    public void deleteUser(User user) {
        this.database.remove(user);
    }

    /**
     * Метод добавляет счет для пользователя по номеру его паспорта.
     * @param passport номер паспорта.
     * @param account счет, который добавляется.
     */
    public void addAccountToUser(String passport, Account account) {
        User key = this.findUserByPassport(passport);
        if (key != null) {
            database.get(key).add(account);
        } else {
            System.out.println("User is not found");
        }
    }

    /**
     * Метод удаляет счет для пользователя по номеру его паспорта.
     * @param passport номер паспорта.
     * @param account счет, который удаляется.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User key = this.findUserByPassport(passport);
        if (key != null) {
            database.get(this.findUserByPassport(passport)).remove(account);
        } else {
            System.out.println("User is not found");
        }
    }

    /**
     * Метод возвращает список счетов пользователя по номеру его паспорта.
     * @param passport номер паспорта.
     * @return возвращает список счетов.
     */
    public List<Account> getUserAccounts(String passport) {
        User key = this.findUserByPassport(passport);
        List<Account> result = new ArrayList<>();
        if (key != null) {
            result = database.get(this.findUserByPassport(passport));
        }
        return result;
    }

    /**
     * Метод для перечисления денег с одного счета на другой.
     * @param srcPassport паспорт пользователя-отправлителя.
     * @param srcRequisite реквизиты счета отправителя.
     * @param destPassport паспорт пользователя-получателя.
     * @param dstRequisite реквизиты счета получателя.
     * @param amount сумма перевода.
     * @return возвращает true/false в случае успешного/ неуспешного перевода.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String dstRequisite, double amount) {
        Account srcAccount = this.findAccount(srcPassport, srcRequisite);
        Account destAccount = this.findAccount(destPassport, dstRequisite);
        return srcAccount != null && destAccount != null && srcAccount.transfer(destAccount, amount);

    }
}
