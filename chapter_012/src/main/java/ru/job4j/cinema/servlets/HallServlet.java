package ru.job4j.cinema.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cinema.models.Account;
import ru.job4j.cinema.models.HallJson;
import ru.job4j.cinema.models.HallPlace;
import ru.job4j.cinema.validation.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class HallServlet extends HttpServlet {
    private ValidateService validator = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = this.getJsonHall();
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.bookSeat(req, resp);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/bookPost.jsp").forward(req, resp);
    }

    private String getJsonHall() throws IOException {
        List<HallPlace> places = new ArrayList<>(this.validator.findAll());
        HallJson hall = new HallJson(places, 3, 5);
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, hall);
        return sw.toString();
    }

    private String bookSeat(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("username");
        String phone = req.getParameter("phone");
        String id = req.getParameter("id");
        Account account = new Account(name, phone);
        String answer;
        try {
            validator.bookSeat(id, account);
            answer = "Место забронировано.";
        } catch (Exception e) {
            answer = e.getMessage();
        }
        return answer;
    }
}
