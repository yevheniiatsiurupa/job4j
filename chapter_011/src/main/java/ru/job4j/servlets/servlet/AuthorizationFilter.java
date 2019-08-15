package ru.job4j.servlets.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Фильтр для авторизации пользователя.
 * @version 1.0.
 * @since 15/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Фильтр для авторизации пользователя.
     * Проверяет атрибут сессии role.
     * Если установлен атрибут admin, то фильтр пропускает запрос, то есть
     * отправляется запрос сервлету по адресу /list.
     * Если установлен другой атрибут role, то фильтр перенаправляет запрос, то есть
     * отправляется запрос сервлету по адресу /listSingleUser.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (session.getAttribute("role").equals("admin")) {
            filterChain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect(String.format("%s/listSingleUser", request.getContextPath()));
        }
    }

    @Override
    public void destroy() {

    }
}
