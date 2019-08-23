package ru.job4j.cinema.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.cinema.models.Account;
import ru.job4j.cinema.models.HallPlace;
import ru.job4j.cinema.validation.PlaceValidationException;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class DbStore {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    private static boolean first = true;

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
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    public static DbStore getInstance() {
        return INSTANCE;
    }

    public void setPlaces() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 5; j++) {
                HallPlace hp = new HallPlace(i, j, i * 100);
                hp.setId(String.format("%d-%d", i, j));
                this.add(hp);
            }
        }
    }

    public void createTable() {
        try {
            Connection connection = SOURCE.getConnection();
            Statement st = connection.createStatement();
            st.execute("create table if not exists accounts("
                    + "phone varchar(100) primary key,"
                    + "name varchar(100));"
                    + "create table if not exists halls("
                    + "id varchar(100) primary key,"
                    + "row integer,"
                    + "place integer,"
                    + "price integer,"
                    + "status varchar(100),"
                    + "account_phone varchar(100) references accounts(phone));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(HallPlace hallPlace) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "insert into halls "
                             + "(row, place, price, status, id) "
                             + "values (?, ?, ?, ?, ?);"
             )) {
            st.setInt(1, hallPlace.getRow());
            st.setInt(2, hallPlace.getPlace());
            st.setInt(3, hallPlace.getPrice());
            st.setString(4, hallPlace.getStatus());
            st.setString(5, hallPlace.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<HallPlace> findAll() {
        Collection<HallPlace> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(
                    "select * from halls;"
            );
            while (rs.next()) {
                int row = rs.getInt("row");
                int place = rs.getInt("place");
                int price = rs.getInt("price");
                String status = rs.getString("status");
                String id = rs.getString("id");
                HallPlace hallPlace = new HallPlace(row, place, price);
                hallPlace.setId(id);
                hallPlace.setStatus(status);
                result.add(hallPlace);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void bookSeat(String seatId, Account account) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "insert into accounts "
                             + "(name, phone) "
                             + "values (?, ?);");
             PreparedStatement st2 = connection.prepareStatement(
                     "update halls set status = ?, account_phone = ? where id = ? and status='free';")) {
            connection.setAutoCommit(false);

            st.setString(1, account.getName());
            st.setString(2, account.getPhone());
            st.executeUpdate();

            st2.setString(1, "occupied");
            st2.setString(2, account.getPhone());
            st2.setString(3, seatId);

            if (st2.executeUpdate() == 1) {
                connection.commit();
            } else {
                connection.rollback();
                throw new PlaceValidationException("");
            }
        } catch (PlaceValidationException e) {
            throw new PlaceValidationException("Место уже забронировано другим пользователем. Попробуйте еще раз.");
        } catch (SQLException e) {
            throw new PlaceValidationException("Номер занят другим пользователем, проверьте ФИО.");
        }
    }

    public static void main(String[] args) {
        DbStore db = DbStore.getInstance();
        db.setPlaces();
    }
}
