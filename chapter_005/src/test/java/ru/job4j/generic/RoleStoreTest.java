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
        Role[] exp1 = {first, second};
        Role[] res1 = cont.findAll();

        Role third = new Role("003");
        cont.replace("001", third);
        Role[] exp2 = {third, second};
        Role[] res2 = cont.findAll();

        cont.delete("002");
        Role[] exp3 = {third};
        Role[] res3 = cont.findAll();

        Role res4 = cont.findById("003");

        assertThat(res1, is(exp1));
        assertThat(res2, is(exp2));
        assertThat(res3, is(exp3));
        assertThat(res4, is(third));
    }
}