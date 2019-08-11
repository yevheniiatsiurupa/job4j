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

public class UserCreateServlet extends HttpServlet {

    /**
     * Метод обрабатывает запрос get сервлету UserCreateServlet.
     * На странице есть форма для создания пользователя.
     * Данные формы обрабатываются методом post сервлету UserCreateServlet
     * (передаваемый параметр: name).
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     * Получаемый параметр: name.
     * Добавляет нового пользователя.
     * Кнопка 'Return to users list' отправляет запрос get сервлету UsersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/createPost.jsp").forward(req, resp);
    }
}
