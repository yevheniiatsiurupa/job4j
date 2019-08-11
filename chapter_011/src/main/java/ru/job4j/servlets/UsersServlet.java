package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Presentation layout. Servlet.
 * @version 1.0.
 * @since 11/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class UsersServlet extends HttpServlet {

    /**
     * Открывает страницу, на которой есть:
     * кнопка "Create new user" - отправляет запрос get сервлету UserCreateServlet,
     * таблица с пользователями, возле каждого по 2 кнопки,
     * кнопка "update" отправляет запрос get сервлету UserUpdateServlet
     * (передаваемые параметры: id, name)
     * кнопка "delete" отправляет запрос get сервлету UserDeleteServlet
     * (передаваемые параметры: id).
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/list.jsp");
    }
}
