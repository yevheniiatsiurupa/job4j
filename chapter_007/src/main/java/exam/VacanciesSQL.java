package exam;


import java.io.InputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import static java.util.Calendar.YEAR;

public class VacanciesSQL implements AutoCloseable {
    /**
     * Поле хранит соединение с определенной базой данных.
     */
    private Connection connection;

    private long defTime;

    public long getDefTime() {
        return defTime;
    }

    /**
     * Метод для подключения к базам данных sql.
     * @return возвращает true, если соединение установлено.
     */
    public boolean init(String propertiesFile) {
        try (InputStream in = VacanciesSQL.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            Properties pr = new Properties();
            pr.load(in);
            Class.forName(pr.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    pr.getProperty("url-parser"),
                    pr.getProperty("username"),
                    pr.getProperty("password")
            );
            this.createTable();
            defTime = this.maxTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.connection != null;
    }

    /**
     * Метод создает таблицу vacancy, если она еще не создана.
     */
    public void createTable() {
        try (Statement st = connection.createStatement()) {
            st.execute("create table if not exists vacancy("
                    + "id serial primary key,"
                    + "name varchar,"
                    + "text varchar,"
                    + "link varchar,"
                    + "vac_time bigint,"
                    + "CONSTRAINT name_constraint UNIQUE (name));");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для добавления вакансии в таблицу vacancy.
     * @param name название вакансии (уникальное).
     * @param text текст вакансии.
     * @param link ссылка на вакансию.
     */
    public void addVacancy(String name, String text, String link, long time) {
        try (PreparedStatement pr = connection.prepareStatement(
                "insert into vacancy (name, text, link, vac_time) values (?, ?, ?, ?);"
        )) {
            pr.setString(1, name);
            pr.setString(2, text);
            pr.setString(3, link);
            pr.setLong(4, time);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long maxTime() {
        long result = 1;
        try (Statement pr = connection.createStatement(

        )) {
            ResultSet rs = pr.executeQuery("select max(vac_time) from vacancy");
            while (rs.next()) {
                result = rs.getLong("max");
            }
            if (result == 0) {
                result = this.getDefaultDate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private long getDefaultDate() {
        Calendar cal = Calendar.getInstance();
        Calendar tmp = new GregorianCalendar(cal.get(YEAR), 0, 01);
        return tmp.getTimeInMillis();
    }



    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VacanciesSQL test = new VacanciesSQL();
        test.init("app.properties");
    }

}
