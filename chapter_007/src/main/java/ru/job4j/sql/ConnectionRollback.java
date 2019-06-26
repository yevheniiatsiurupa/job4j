package ru.job4j.sql;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс, позволяющий откатывать все коммиты в базу данных.
 * Используется для интеграционных тестов.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 26/06/2019
 */

public class ConnectionRollback {
    /**
     * Метод возвращает объект Connection с режимом autocommit=false и откатом изменений при закрытии соединения.
     * Создает объект прокси, куда передается класс Connection.
     * При вызове метода close из класса Connection будет перед ним вызван метод rollback.
     * При вызове других методов они будут просто выполняться.
     * @param connection исходное соединение.
     * @return возвращает Connection объект.
     * @throws SQLException возможное исключение.
     */
    public static Connection create(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollback.class.getClassLoader(),
                new Class[] {Connection.class},
                (proxy, method, args) -> {
                    Object rsl = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        rsl = method.invoke(connection, args);
                    }
                    return rsl;
                }
        );
    }
}
