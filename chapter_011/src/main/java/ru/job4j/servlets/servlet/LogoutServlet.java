package ru.job4j.servlets.servlet;

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
public class LogoutServlet extends HttpServlet {
    /**
     * Метод для log out пользователя.
     * Удаляет атрибут login и переходит на страницу для аутентификации.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("login");
        resp.sendRedirect(String.format("%s/login", req.getContextPath()));
    }
}
