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

public class UserUpdateServlet extends HttpServlet {
    /**
     * Поле хранит ссылку на объект валидатора.
     */
    private final Validate validator = ValidateService.getInstance();

    /**
     * Метод обрабатывает запрос get сервлету UserUpdateServlet.
     * Получаемый параметр: id, name.
     * На странице есть текст "Update User with id =  ..." и
     * форма с полем name (уже заполнено старым значением) и
     * кнопка для подтверждения редактирования, которая отправляет запрос post
     * сервлету UserUpdateServlet (передаваемый параметр: id, name).
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        pw.append("<html>"
                + "<head>"
                + "<title>Update User</title>"
                + "</head>"
                + "<body>"
                + "<form action = '" + req.getContextPath() + "/edit' method='post'>"
                + "<p>" + "Update User with id = " + id + ":<br>"
                + "Name : <input type='text' name='name' value='" + name + "'/>"
                + "<input type='hidden' name='id' value='" + id + "' />"
                + "<input type='submit'/>" + "</p>"
                + "</form>"
                + "</body>"
                + "</html>");
        pw.flush();
    }

    /**
     * Метод обрабатывает запрос post.
     * Получаемый параметр: id, name.
     * Редактирует элемент по номеру.
     * Кнопка 'Return to users list' отправляет запрос get сервлету UsersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = this.update(req, resp);
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append("<html>"
                + "<head>"
                + "<title>Update User</title>"
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
     * Метод для удаления пользователя.
     * Считывает передаваемый параметр id, name.
     * Редактирует пользователя по номеру (через валидатор).
     * Возвращает результат операции.
     */
    private String update(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("id"));
        String response;
        User user = new User(name);
        try {
            this.validator.update(user, id);
            response = String.format("User with id %d was updated. New name - %s",
                    user.getId(), user.getName());
        } catch (UserValidationException e) {
            response = e.getMessage();
        }
        return response;
    }
}
