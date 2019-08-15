package ru.job4j.servlets.servlet;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.validation.UserValidationException;
import ru.job4j.servlets.validation.Validate;
import ru.job4j.servlets.validation.ValidateService;
import ru.job4j.servlets.validation.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {
    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException, UserValidationException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when((ValidateService.getInstance())).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("user1");
        when(req.getParameter("login")).thenReturn("log1");
        when(req.getParameter("email")).thenReturn("em1");
        when(req.getParameter("password")).thenReturn("pass1");
        when(req.getParameter("role")).thenReturn("role1");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("user1"));
    }

}