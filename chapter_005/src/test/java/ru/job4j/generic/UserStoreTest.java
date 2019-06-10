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

        User third = new User("003");
        cont.replace("001", third);


        cont.delete("002");

        User res = cont.findById("003");

        assertThat(res, is(third));
    }
}