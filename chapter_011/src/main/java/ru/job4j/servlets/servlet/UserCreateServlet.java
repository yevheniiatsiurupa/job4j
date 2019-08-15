package ru.job4j.servlets.servlet;

import ru.job4j.servlets.models.Role;
import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;
import ru.job4j.servlets.validation.ValidateService;

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
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     * Получаемый параметр: name.
     * Добавляет нового пользователя.
     * Кнопка 'Return to users list' отправляет запрос get сервлету UsersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.create(req, resp);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/createPost.jsp").forward(req, resp);
    }

    /**
     * Метод для добавления пользователя.
     * Считывает передаваемый параметр name.
     * Добавляет пользователя, присваивает ему номер id (через валидатор).
     * Возвращает результат операции.
     */
    private String create(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Role role = new Role(req.getParameter("role"));
        long createTime = System.currentTimeMillis();
        String response;
        User user = new User(name, login, email, password, createTime, role);
        try {
            ValidateService.getInstance().add(user);
            response = "User was added.";
        } catch (UserValidationException e) {
            response = e.getMessage();
        }
        return response;
    }
}
