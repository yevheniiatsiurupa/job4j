package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Presentation layout. Servlet.
 * @version 1.0.
 * @since 11/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class UserCreateServlet extends HttpServlet {
    /**
     * Поле хранит ссылку на объект валидатора.
     */
    private final Validate validator = ValidateService.getInstance();

    /**
     * Метод обрабатывает запрос get сервлету UserCreateServlet.
     * На странице есть форма для создания пользователя.
     * Данные формы обрабатываются методом post сервлету UserCreateServlet
     * (передаваемый параметр: name).
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append("<html>"
                + "<head>"
                + "<title>Create User</title>"
                + "</head>"
                + "<body>"
                + "<form action = '" + req.getContextPath() + "/create' method='post'>"
                + "Create User:"
                + "<p>" + "Name : <input type='text' name='name'/>"
                + "<input type='submit' />" + "</p>"
                + "</form>"
                + "</body>"
                + "</html>");
        pw.flush();
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
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        String response = this.create(req, resp);
        pw.append("<html>"
                + "<head>"
                + "<title>Create User</title>"
                + "</head>"
                + "<body>"
                + "<form action = '" + req.getContextPath() + "/list' method='get'>"
                + "<p>" + response + "<br>"
                + "<input type='submit' value='Return to users list' />" + "</p>"
                + "</form>"
                + "</body>"
                + "</html>");
        pw.flush();
    }

    /**
     * Метод для добавления пользователя.
     * Считывает передаваемый параметр name.
     * Добавляет пользователя, присваивает ему номер id (через валидатор).
     * Возвращает результат операции.
     */
    private String create(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String response;
        User user = new User(name);
        try {
            this.validator.add(user);
            response = "User was added. User id = " + user.getId();
        } catch (UserValidationException e) {
            response = e.getMessage();
        }
        return response;
    }
}
