package ru.job4j.servlets.servlet;

import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;
import ru.job4j.servlets.validation.ValidateService;

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
public class UserSingleServlet extends HttpServlet {
    /**
     * Сервлет получает атрибут Login сессии,
     * по нему находит пользователя и переходит на страницу, которая отображает
     * данные только одного пользователя.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        try {
            User user = ValidateService.getInstance().findByLogin(login);
            req.setAttribute("answer", "ok");
            req.setAttribute("user", user);
        } catch (UserValidationException e) {
            req.setAttribute("answer", e.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/views/listSingleUser.jsp").forward(req, resp);
    }

}
