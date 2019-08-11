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

public class UserUpdateServlet extends HttpServlet {

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
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     * Получаемый параметр: id, name.
     * Редактирует элемент по номеру.
     * Кнопка 'Return to users list' отправляет запрос get сервлету UsersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/updatePost.jsp").forward(req, resp);
    }
}
