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

public class UserUpdateServlet extends HttpServlet {

    /**
     * Метод обрабатывает запрос get сервлету UserUpdateServlet.
     * На странице есть текст "Update User with id =  ..." и
     * форма с полем name (уже заполнено старым значением) и
     * кнопка для подтверждения редактирования, которая отправляет запрос post
     * сервлету UserUpdateServlet.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     * Редактирует элемент по номеру.
     * Кнопка 'Return to users list' отправляет запрос get сервлету UsersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.update(req, resp);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/updatePost.jsp").forward(req, resp);
    }

    /**
     * Метод для удаления пользователя.
     * Считывает передаваемый параметр id, name.
     * Редактирует пользователя по номеру (через валидатор).
     * Возвращает результат операции.
     */
    private String update(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        Role role = new Role(req.getParameter("role"));
        long createTime = System.currentTimeMillis();
        String response;
        User user = new User(name, login, email, password, createTime, role);
        user.setCity(city);
        user.setCountry(country);
        try {
            ValidateService.getInstance().update(user, id);
            response = "User was successfully updated.";
        } catch (UserValidationException e) {
            response = e.getMessage();
        }
        return response;
    }
}
