package ru.job4j.servlets.servlet;

import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;
import ru.job4j.servlets.validation.Validate;
import ru.job4j.servlets.validation.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Presentation layout. Servlet.
 * @version 1.0.
 * @since 08/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class UserServlet extends HttpServlet {
    /**
     * Поле хранит ссылку на объект валидатора.
     */
    private final Validate validator = ValidateService.getInstance();

    /**
     * Поле хранит ссылку на карту содержащую ключи действий и функции,
     * реализующие действия.
     */
    private final Map<String, Function<HttpServletRequest, String>> dispatch;

    /**
     * Конструктор.
     * При создании сервлета добавляются в карту ключи действий и функции.
     */
    public UserServlet() {
        this.dispatch = new ConcurrentHashMap<>();
        this.dispatch.put("add", this.addUser());
        this.dispatch.put("update", this.updateUser());
        this.dispatch.put("delete", this.deleteUser());
    }

    /**
     * Метод возращает ответ на запрос GET.
     * В ответ на данный запрос возвращаются все добавленные пользователи.
     * @param req http запрос.
     * @param resp http ответ.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response;
        try {
            Collection<User> users = this.validator.findAll();
            response = users.toString();
        } catch (UserValidationException e) {
            response = e.getMessage();
        }
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(response);
        pw.flush();
    }

    /**
     * Метод возвращает ответ на запрос POST.
     * Данный запрос может добавлять, редактировать и удалять пользователя.
     * Конкретное действие выбирается по ключу,
     * который получают из параметра action запроса.
     * В зависимости от ключа получаем функцию из карты dispatch,
     * которая выполняет нужное действие.
     * @param req http запрос.
     * @param resp http ответ.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String response = this.dispatch.get(action).apply(req);
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(response);
        pw.flush();
    }

    /**
     * Метод для добавления пользователя.
     * Возвращаемая функция выполняет следующие действия:
     * получает из параметров запроса имя,
     * создает пользователя для добавления,
     * добавляет его с помощью объекта validator.
     * @return возвращает функцию, которая принимает запрос,
     * выполняет действия по добавлению пользователя,
     * возращает сообщение, был ли добавлен пользователь.
     */
    public Function<HttpServletRequest, String> addUser() {
        return req -> {
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
        };
    }

    /**
     * Метод для редактирования пользователя.
     * Возвращаемая функция выполняет следующие действия:
     * получает из параметров запроса имя и номер пользователя,
     * создает пользователя для добавления,
     * редактирует пользователя с номером Id с помощью объекта validator.
     * @return возвращает функцию, которая редактирует пользователя.
     */
    public Function<HttpServletRequest, String> updateUser() {
        return req -> {
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
        };
    }

    /**
     * Метод для удаления пользователя.
     * Возвращаемая функция выполняет следующие действия:
     * получает из параметров запроса номер пользователя,
     * удаляет его с помощью объекта validator.
     * @return возвращает функцию, которая удаляет пользователя.
     */
    public Function<HttpServletRequest, String> deleteUser() {
        return req -> {
            int id = Integer.parseInt(req.getParameter("id"));
            String response;
            try {
                this.validator.delete(id);
                response = String.format("User with id %d was deleted.", id);
            } catch (UserValidationException e) {
                response = e.getMessage();
            }
            return response;
        };
    }
}
