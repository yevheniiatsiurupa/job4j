package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 08/06/2019
 */

public class RoleStoreTest {
    /**
     * Test RoleStore.
     */
    @Test
    public void whenInputRoleThenContainRole() {
        Role first = new Role("001");
        Role second = new Role("002");

        RoleStore cont = new RoleStore();
        cont.add(first);
        cont.add(second);

        Role third = new Role("003");
        cont.replace("001", third);

        cont.delete("002");

        Role res = cont.findById("003");

        assertThat(res, is(third));
    }
}