package ru.job4j.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 02/07/2019
 */

public class SimpleGeneratorTest {
    /**
     * Test generate.
     */
    @Test
    public void whenInputNameAndSubjectThenShowResult() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String text = "I am ${name}, Who are ${subject}?";
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("name", "Jane");
        inputMap.put("subject", "you");
        String expected = "I am Jane, Who are you?";

        String result = simpleGenerator.generate(text, inputMap);

        assertThat(result, is(expected));
    }

    /**
     * Test generate.
     */
    @Test
    public void whenInputWordThenShowResult() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("sos", "Aaaa");
        String expected = "Help, Aaaa, Aaaa, Aaaa";

        String result = simpleGenerator.generate(text, inputMap);

        assertThat(result, is(expected));
    }
}