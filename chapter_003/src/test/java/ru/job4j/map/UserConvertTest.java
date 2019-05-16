package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        List<User> list = new ArrayList<>();
        User user01 = new User(123, "John", "London");
        User user02 = new User(124, "Mary", "NewYork");
        list.add(user01);
        list.add(user02);
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> result = userConvert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(123, user01);
        expected.put(124, user02);
        assertThat(result, is(expected));
    }
}
