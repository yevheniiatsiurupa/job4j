package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 16/05/2019
 */
public class UserConvertTest {
    /**
     * Test process.
     */
    @Test
    public void whenPutListThenHashMap() {
        User user01 = new User(123, "John", "London");
        User user02 = new User(124, "Mary", "NewYork");
        List<User> list = Arrays.asList(user01, user02);
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> result = userConvert.process(list);
        Map<Integer, User> expected = Map.of(123, user01, 124, user02);
        assertThat(result, is(expected));
    }
}
