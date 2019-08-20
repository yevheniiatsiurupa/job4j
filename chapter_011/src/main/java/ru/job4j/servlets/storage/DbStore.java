package ru.job4j.servlets.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.models.Role;
import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;

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
    private static boolean firstCall = true;

    /**
     * Конструктор.
     * При создании объекта DbStore создается пул соединений с базой данных.
     * Пул соединений реализован с помощью объекта BasicDataSource.
     * Создается таблица в базе данных для храниения информации о пользователях.
     */
    private DbStore() {
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
            if (firstCall) {
                User admin = new User(
                        "Name1", "admin1", "email1",
                        "adminpass", System.currentTimeMillis(), new Role("admin"));
                admin.setCity("Moscow");
                admin.setCountry("Russia");
                this.add(admin);
                firstCall = false;
            }
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
                    + "password varchar(100),"
                    + "city varchar(100),"
                    + "country varchar(100),"
                    + "role varchar(100),"
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
    public void add(User user) throws UserValidationException {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "insert into users_data "
                        + "(name, login, email, password, city, country, role, time_of_creation) "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?);"
        )) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getCity());
            st.setString(6, user.getCountry());
            st.setString(7, user.getRole().getName());
            st.setLong(8, user.getCreateDate());
            st.executeUpdate();

        } catch (Exception e) {
            throw new UserValidationException("Cannot add User. Login is used.");
        }
    }

    /**
     * Метод создает и выполняет запрос на редактирование пользователя в БД.
     */
    @Override
    public boolean update(User user, int id) throws UserValidationException {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "update users_data set name = ?, login = ?, email = ?, "
                        + "password = ?, city = ?, country = ?, role = ?, time_of_creation = ? "
                        + "where id = ?;"
        )) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getCity());
            st.setString(6, user.getCountry());
            st.setString(7, user.getRole().getName());
            st.setLong(8, user.getCreateDate());
            st.setInt(9, id);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw new UserValidationException("Cannot update User. Login is used.");
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
                String password = rs.getString("password");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String role = rs.getString("role");
                long time = rs.getLong("time_of_creation");
                int id = rs.getInt("id");
                User user = new User(id, name, login, email, password, time, new Role(role));
                user.setCity(city);
                user.setCountry(country);
                result.add(user);
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
                String password = rs.getString("password");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String role = rs.getString("role");
                long time = rs.getLong("time_of_creation");
                int idRes = rs.getInt("id");
                result = new User(idRes, name, login, email, password, time, new Role(role));
                result.setCity(city);
                result.setCountry(country);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByLogin(String login) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "select * from users_data where login = ?;"
             )) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String role = rs.getString("role");
                long time = rs.getLong("time_of_creation");
                int idRes = rs.getInt("id");
                result = new User(idRes, name, login, email, password, time, new Role(role));
                result.setCity(city);
                result.setCountry(country);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
