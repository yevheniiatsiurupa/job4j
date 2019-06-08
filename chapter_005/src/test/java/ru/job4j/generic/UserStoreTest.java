package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 08/06/2019
 */

public class UserStoreTest {
    /**
     * Test UserStore.
     */
    @Test
    public void whenInputUserThenContainUser() {
        User first = new User("001");
        User second = new User("002");
        UserStore cont = new UserStore();
        cont.add(first);
        cont.add(second);
        User[] exp1 = {first, second};
        User[] res1 = cont.findAll();

        User third = new User("003");
        cont.replace("001", third);
        User[] exp2 = {third, second};
        User[] res2 = cont.findAll();

        cont.delete("002");
        User[] exp3 = {third};
        User[] res3 = cont.findAll();

        User res4 = cont.findById("003");

        assertThat(res1, is(exp1));
        assertThat(res2, is(exp2));
        assertThat(res3, is(exp3));
        assertThat(res4, is(third));
    }
}