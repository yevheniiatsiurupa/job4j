package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 19/05/2019
 */

public class BankTest {
    /**
     * Test transfer.
     */
    @Test
    public void whenTransferThenChangeValue() {
        Bank bank = new Bank();
        User one = new User("John Doe", "123456");
        User two = new User("John Smith", "567890");
        bank.addUser(one);
        bank.addUser(two);
        Account accOne = new Account(2000d, "123");
        Account accTwo = new Account(3000d, "125");
        bank.addAccountToUser("123456", accOne);
        bank.addAccountToUser("567890", accTwo);
        bank.transferMoney("123456", "123", "567890", "125", 1000d);
        assertThat(accOne.getValues(), is(1000d));

    }

    /**
     * Test transfer.
     */
    @Test
    public void whenTransferMoreThenFalse() {
        Bank bank = new Bank();
        User one = new User("John Doe", "123456");
        User two = new User("John Smith", "567890");
        bank.addUser(one);
        bank.addUser(two);
        Account accOne = new Account(2000d, "123");
        Account accTwo = new Account(3000d, "125");
        bank.addAccountToUser("123456", accOne);
        bank.addAccountToUser("567890", accTwo);
        boolean result = bank.transferMoney("123456", "123", "567890", "125", 3000d);
        assertThat(result, is(false));
    }

    /**
     * Test transfer.
     */
    @Test
    public void whenTransferWrongRequisitesThenFalse() {
        Bank bank = new Bank();
        User one = new User("John Doe", "123456");
        User two = new User("John Smith", "567890");
        bank.addUser(one);
        bank.addUser(two);
        Account accOne = new Account(2000d, "123");
        Account accTwo = new Account(3000d, "125");
        bank.addAccountToUser("123456", accOne);
        bank.addAccountToUser("567890", accTwo);
        boolean result = bank.transferMoney("123456", "123", "567890", "126", 1000d);
        assertThat(result, is(false));
    }

    /**
     * Test deleteAccountFromUser.
     */
    @Test
    public void whenDeleteThenNotFound() {
        Bank bank = new Bank();
        User one = new User("John Doe", "123456");
        bank.addUser(one);
        Account accOne = new Account(2000d, "123");
        Account accTwo = new Account(3000d, "125");
        bank.addAccountToUser("123456", accOne);
        bank.addAccountToUser("123456", accTwo);
        bank.deleteAccountFromUser("123456", accOne);
        List<Account> result = bank.getUserAccounts("123456");
        List<Account> expected = new ArrayList<>();
        expected.add(accTwo);
        assertThat(result, is(expected));
    }
}
