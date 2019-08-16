package ru.job4j.servlets.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.models.Role;
import ru.job4j.servlets.models.User;
import ru.job4j.servlets.validation.UserValidationException;
import ru.job4j.servlets.validation.Validate;
import ru.job4j.servlets.validation.ValidateService;
import ru.job4j.servlets.validation.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version 1.0.
 * @since 16/08/2019.
 * @author Evgeniya Tsiurupa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserDeleteServletTest {
    @Test
    public void whenDeleteUserThenRemoved() throws ServletException, IOException, UserValidationException {
        Validate validate = new ValidateStub();
        validate.add(new User("user1", "log1",
                "email1", "pass1",
                System.currentTimeMillis(), new Role("user")));
        PowerMockito.mockStatic(ValidateService.class);
        when((ValidateService.getInstance())).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getRequestDispatcher("/WEB-INF/views/deletePost.jsp")).thenReturn(mock(RequestDispatcher.class));
        new UserDeleteServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }
}