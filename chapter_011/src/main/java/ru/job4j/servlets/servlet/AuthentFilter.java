package ru.job4j.servlets.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр для аутентификации пользователя.
 * @version 1.0.
 * @since 15/08/2019.
 * @author Evgeniya Tsiurupa
 */
public class AuthentFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Фильтр проверяет URI запроса, если запрос идет на страницу /login или /create, то
     * его фильтр пропускает и происходит переход на указанные страницы.
     * Если в запросе идет переход на другие страницы, то проверяется залогинился ли
     * пользователь с помощью проверки атрибутов сессии. Если установлен атрибут
     * login, то пользователь уже ранее залогинился и можно производить переход на
     * нужную страницу.
     * Если у сессии нет атрибута login, то пользователя направляют на страницу /login.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/create")) {
            filterChain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/login", request.getContextPath()));
                return;
            }
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
