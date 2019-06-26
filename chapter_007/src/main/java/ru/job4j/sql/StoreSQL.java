package ru.job4j.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий методы для работы с базой данных SQLLite.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 24/06/2019
 */

public class StoreSQL implements  AutoCloseable {
    /**
     * Поле хранит объект с настройками.
     */
    private final Config config;

    /**
     * Поле хранит соединение с базой данных.
     */
    private Connection connection;

    /**
     * Конструктор получает объект config, который хранит список настроек.
     * @param config объект класса Config.
     */
    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * Метод создает подключение к базе данных и создает таблицу entry, если она отсутствовала.
     * @param fileName название базы данных.
     */
    public void init(String fileName) {
        String url = config.get("url-sqlite") + fileName;
        try {
            Connection conn = DriverManager.getConnection(url);
            this.connection = conn;
            this.createTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * Метод создает таблицу entry, если она еще не создана.
     */
    private void createTable() {
        try (Statement st = connection.createStatement()) {
            st.execute("create table if not exists entry("
                    + "field integer);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записывает в таблицу entry N строк, заполняет их числами от 1 до n.
     * @param size количество записанных строк.
     */
    public void generate(int size) throws Exception{
        this.createTable();
        try (Statement statement = connection.createStatement();
                PreparedStatement st = connection.prepareStatement(
                "insert into entry (field) values (?);"
        )) {
            statement.executeUpdate("delete from entry;");
            connection.setAutoCommit(false);
            for (int n = 1; n <= size; n++) {
                st.setInt(1, n);
                st.addBatch();
            }
            st.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    /**
     * Метод получает список записей из таблицы entry.
     */
    public List<Entries.Entry> load() {
        List<Entries.Entry> result = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from entry;");
            while (rs.next()) {
                Entries.Entry tmp = new Entries.Entry(rs.getInt("field"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception{
        Config config = new Config();
        config.init();
        StoreSQL store = new StoreSQL(config);
        store.init("store");
        store.generate(5);
        List<Entries.Entry> result = store.load();
        for (Entries.Entry tmp : result) {
            System.out.println(tmp.getField());
        }
    }
}
