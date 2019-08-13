package ru.job4j.servlets.servlet;

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

public class UserDeleteServlet extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/views/delete.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     * Получаемый параметр: id.
     * Удаляет элемент по номеру.
     * Кнопка 'Return to users list' отправляет запрос get сервлету UsersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.delete(req, resp);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/deletePost.jsp").forward(req, resp);
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
            ValidateService.getInstance().delete(id);
            response = "User was successfully deleted.";
        } catch (UserValidationException e) {
            response = e.getMessage();
        }
        resp.setContentType("text/html");
        return response;
    }
}
