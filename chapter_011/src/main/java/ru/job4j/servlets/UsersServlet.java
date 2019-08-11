package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Presentation layout. Servlet.
 * @version 1.0.
 * @since 11/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class UsersServlet extends HttpServlet {
    /**
     * Поле хранит ссылку на объект валидатора.
     */
    private final Validate validator = ValidateService.getInstance();

    /**
     * Открывает страницу, на которой есть:
     * кнопка "Create new user" - отправляет запрос get сервлету UserCreateServlet,
     * таблица с пользователями, возле каждого по 2 кнопки,
     * кнопка "update" отправляет запрос get сервлету UserUpdateServlet
     * (передаваемые параметры: id, name)
     * кнопка "delete" отправляет запрос get сервлету UserDeleteServlet
     * (передаваемые параметры: id).
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        String table = this.createUsersTable(req, resp);
        pw.append("<html>"
                + "<head>"
                + "<title>Users page</title>"
                + "</head>"
                + "<body>"
                + "<form action = '" + req.getContextPath() + "/create' method='get'>"
                + "<input type='submit' value='Create new user' />"
                + "</form>"
                + "<p>" + "<br>" + table + "</p>"
                + "</body>"
                + "</html>");
        pw.flush();
    }

    /**
     * Метод создает таблицу со списком пользователей и кнопками редактировать и удалить.
     * Создание таблицы:
     * <table> создаем таблицу
     *     <tr><td> ... </tr></td> создаем новую строку и новую ячейку
     * </table>
     *
     * Создание кнопки редактирования:
     * <form action = .../edit' method='get'>
     *      данные формы обрабатывает сервлет с адресом /edit запрос get
     * <input type='hidden' name='id' value='tmp.getId()'/>
     *      передаем скрытый параметр id=номер пользователя.
     * <input type='hidden' name='name' value='tmp.getName()'/>
     *      передаем скрытый параметр name=имя пользователя.
     * <input type='submit' value='update'> </form>
     *      кнопка для отправки данных формы на сервер, текст на кнопке update.
     *
     * @return таблицу в html
     */
    private String createUsersTable(HttpServletRequest req, HttpServletResponse resp) {
        StringBuilder sb = new StringBuilder("<table>");
        try {
            Collection<User> users = this.validator.findAll();
            for (User tmp : users) {
                sb.append("<tr><td align='right' valign='top'>User name: " + tmp.getName() + "</td>");
                sb.append("<td align='right' valign='center'> "
                        + "<form action = '" + req.getContextPath() + "/edit' method='get'>"
                        + "<input type='hidden' name='id' value='" + tmp.getId() + "'/>"
                        + "<input type='hidden' name='name' value='" + tmp.getName() + "'/>"
                        + "<input type='submit' value='update'>" + "</form>"
                        + "</td>");
                sb.append("<td align='right' valign='center'> "
                        + "<form action = '" + req.getContextPath() + "/delete' method='get'>"
                        + "<input type='hidden' name='id' value='" + tmp.getId() + "'/>"
                        + "<input type='submit' value='delete' />" + "</form>"
                        + "</td>");
                sb.append("</tr>");
            }
        } catch (UserValidationException e) {
            sb.append("<tr><td>User name: " + e.getMessage() + "</td></tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

}
