package ru.job4j.bank;

import java.util.*;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 19/05/2019
 */

public class Bank {
    /**
     * Поле содержит хранилище всех счетов отдельных клиентов.
     */
    private Map<User, List<Account>> database = new TreeMap<>();


    public User findUserByPassport(String passport) {
        Set<User> tmp = database.keySet();
        User key = null;
        for (User temp : tmp) {
            if (temp.getPassport().equals(passport)) {
                key = temp;
            }
        }
        return key;
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
        database.get(this.findUserByPassport(passport)).add(account);
    }

    /**
     * Метод удаляет счет для пользователя по номеру его паспорта.
     * @param passport номер паспорта.
     * @param account счет, который удаляется.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        database.get(this.findUserByPassport(passport)).remove(account);
    }

    /**
     * Метод возвращает список счетов пользователя по номеру его паспорта.
     * @param passport номер паспорта.
     * @return возвращает список счетов.
     */
    public List<Account> getUserAccounts(String passport) {
        return database.get(this.findUserByPassport(passport));
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
        User o1 = this.findUserByPassport(srcPassport);
        User o2 = this.findUserByPassport(destPassport);
        List<Account> a1 = database.get(o1);
        List<Account> a2 = database.get(o2);
        Account actual1 = null;
        Account actual2 = null;
        boolean checkAccount1 = false;
        boolean checkAccount2 = false;
        for (Account tmp : a1) {
            if (tmp.getRequisites().equals(srcRequisite)) {
                actual1 = tmp;
                checkAccount1 = true;
                break;
            }
        }
        for (Account tmp : a2) {
            if (tmp.getRequisites().equals(dstRequisite)) {
                actual2 = tmp;
                checkAccount2 = true;
                break;
            }
        }
        return checkAccount1 && checkAccount2 && actual1.transfer(actual2, amount);

    }
}
