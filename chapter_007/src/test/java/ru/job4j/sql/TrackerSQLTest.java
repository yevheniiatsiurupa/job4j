package ru.job4j.sql;

import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    /**
     * Метод возвращает соединение Connection.
     * @return Connection соединение к адресу с использованием имени пользователя и пароля.
     */
    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Тест. В качестве соединения Connection в конструктор TrackerSQL передается соединение,
     * которое возвращается при вызове метода create из класса ConnectionRollback.
     * Это соединение является прокси объектом, который перехватывает вызовы всех методов
     * и меняет действия пр вызове метода close (выполняет откат).
     * @throws SQLException
     */
    @Test
    public void createItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc", 123L));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }
        @Test
        public void checkConnection() throws SQLException {
            TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
            assertThat(sql.init(), is(true));
        }

}