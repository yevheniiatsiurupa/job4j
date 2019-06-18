package ru.job4j.io;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 16/06/2019
 */

public class ConfigTest {
    /**
     * Test load / value.
     */
    @Test
    public void whenReadFromFileThenPutKeysToMap() {
        Config testConf = new Config("./../app.properties");
        testConf.load();
        assertThat(testConf.value("hibernate.connection.driver_class"),
                is("org.postgresql.Driver"));
        assertThat(testConf.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
    }
}