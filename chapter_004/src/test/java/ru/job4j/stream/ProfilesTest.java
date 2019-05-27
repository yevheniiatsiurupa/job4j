package ru.job4j.stream;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.List;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 27/05/2019
 */

public class ProfilesTest {
    /**
     * Test collect.
     */
    @Test
    public void whenPutProfilesThenReturnAddress() {
        Address one = new Address("city1", "street1", 10, 11);
        Address two = new Address("city2", "street2", 20, 22);
        List<Profile> testProfile = List.of(new Profile(one), new Profile(two));
        Profiles test = new Profiles();
        List<Address> result = test.collect(testProfile);
        List<Address> expected = List.of(one, two);
        assertThat(result, is(expected));
    }
}
