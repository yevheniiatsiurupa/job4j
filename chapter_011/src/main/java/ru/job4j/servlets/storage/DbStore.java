package ru.job4j.servlets.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * @version 1.0.
 * @since 12/08/2019.
 * @author Evgeniya Tsiurupa
 */
public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    /**
     * Конструктор.
     * При создании объекта DbStore создается пул соединений с базой данных.
     * Пул соединений реализован с помощью объекта BasicDataSource.
     * Создается таблица в базе данных для храниения информации о пользователях.
     */
    public DbStore() {
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
            this.createTable();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод добавляет таблицу для хранения информации о пользователях,
     * если она еще не была создана.
     */
    public void createTable() {
        try {
            Connection connection = SOURCE.getConnection();
            Statement st = connection.createStatement();
            st.execute("create table if not exists users_data("
                    + "id serial primary key,"
                    + "name varchar(100),"
                    + "login varchar(100) unique,"
                    + "email varchar(100) unique,"
                    + "time_of_creation bigint);");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод создает и выполняет запрос на добавление нового пользователя в БД.
     * Пользователю присваивается уникальный номер.
     * @param user добавляемый пользователь.
     */
    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "insert into users_data (name, login, email, time_of_creation) values (?, ?, ?, ?);"
        )) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setLong(4, user.getCreateDate());
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает и выполняет запрос на редактирование пользователя в БД.
     */
    @Override
    public boolean update(User user, int id) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "update users_data set name = ?, login = ?, email = ?, time_of_creation = ? where id = ?;"
        )) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setLong(4, user.getCreateDate());
            st.setInt(5, id);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "delete from users_data where id = ?;"
        )) {
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(
                    "select * from users_data;"
            );
            while (rs.next()) {
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                long time = rs.getLong("time_of_creation");
                int id = rs.getInt("id");
                result.add(new User(id, name, login, email, time));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "select * from users_data where id = ?;"
        )) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                long time = rs.getLong("time_of_creation");
                int idRes = rs.getInt("id");
                result = new User(idRes, name, login, email, time);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
