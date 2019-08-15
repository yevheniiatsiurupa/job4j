package ru.job4j.servlets.servlet;

import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;
import ru.job4j.servlets.validation.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

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
     * кнопка "delete" отправляет запрос get сервлету UserDeleteServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            Collection<User> users = ValidateService.getInstance().findAll();
            req.setAttribute("answer", "ok");
            req.setAttribute("users", users);
        } catch (UserValidationException e) {
            req.setAttribute("answer", e.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }
}
