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

public class UserDeleteServlet extends HttpServlet {
    /**
     * Поле хранит ссылку на объект валидатора.
     */
    private final Validate validator = ValidateService.getInstance();

    /**
     * Метод обрабатывает запрос get сервлету UserDeleteServlet.
     * Получаемый параметр: id.
     * На странице есть текст "Deleting User with id = ..." и
     * кнопка для подтверждения удаления, которая отправляет запрос post
     * сервлету UserDeleteServlet (передаваемый параметр: id).
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        pw.append("<html>"
                + "<head>"
                + "<title>Delete User</title>"
                + "</head>"
                + "<body>"
                + "<form action = '" + req.getContextPath() + "/delete' method='post'>"
                + "<p>" + "Deleting User with id = " + id + ":<br>"
                + "<input type='hidden' name='id' value='" + id + "'>"
                + "<input type='submit' value='Confirm'>" + "</p>"
                + "</form>"
                + "</body>"
                + "</html>");
        pw.flush();
    }

    /**
     * Метод обрабатывает запрос post.
     * Получаемый параметр: id.
     * Удаляет элемент по номеру.
     * Кнопка 'Return to users list' отправляет запрос get сервлету UsersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = this.delete(req, resp);
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append("<html>"
                + "<head>"
                + "<title>Delete User</title>"
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
     * Считывает передаваемый параметр id.
     * Удаляет пользователя по номеру (через валидатор).
     * Возвращает результат операции.
     */
    private String delete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String response;
        try {
            this.validator.delete(id);
            response = String.format("User with id = %d was deleted.", id);
        } catch (UserValidationException e) {
            response = e.getMessage();
        }
        resp.setContentType("text/html");
        return response;
    }
}
