package ru.job4j.servlets.servlet;

import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;
import ru.job4j.servlets.validation.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version 1.0.
 * @since 15/08/2019.
 * @author Evgeniya Tsiurupa
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    /**
     * Метод получает параметры login, password из запроса.
     * По полученному логину находится соответствующий пользователь и проверяется
     * пароль, если все верно, то сессии устанавливаются атрибуты login, role.
     * Атрибут login будет использоваться, чтобы проверить произвел ли пользователь вход.
     * Атрибут role будет использоваться, чтобы проверить имеет ли пользователь доступ к определенным страницам.
     * Если пользователь не найден по логину или пароль неверный,
     * то устанавливается атрибут error для запроса и вызывается снова метод doGet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User search = ValidateService.getInstance().findByLogin(login);
            if (password.equals(search.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                session.setAttribute("role", search.getRole().getName());
                resp.sendRedirect(String.format("%s/list", req.getContextPath()));
            } else {
                req.setAttribute("error", "Password is incorrect.");
                doGet(req, resp);
            }
        } catch (UserValidationException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }
    }
}
