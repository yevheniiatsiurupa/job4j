package ru.job4j.servlets;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

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
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.createTable();
    }

    /**
     * Поле хранит следующий номер для присвоения следующему пользователю,
     * который будет добавлен в хранилище.
     */
    private final AtomicInteger nextId = new AtomicInteger(0);

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
                    + "id integer primary key,"
                    + "name varchar(100),"
                    + "login varchar(100),"
                    + "email varchar(100),"
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
        user.setId(this.nextId.getAndIncrement());
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "insert into users_data (id, name, login, email, time_of_creation) values (?, ?, ?, ?, ?);"
        )) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setLong(5, user.getCreateDate());
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает и выполняет запрос на редактирование пользователя в БД.
     */
    @Override
    public void update(User user, int id) {
        user.setId(id);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                "delete from users_data where id = ?;"
        )) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                String email = rs.getString("login");
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
                String email = rs.getString("login");
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
