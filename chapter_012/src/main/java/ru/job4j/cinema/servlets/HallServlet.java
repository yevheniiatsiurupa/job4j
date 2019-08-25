package ru.job4j.cinema.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cinema.models.Account;
import ru.job4j.cinema.models.FormJson;
import ru.job4j.cinema.models.HallJson;
import ru.job4j.cinema.models.HallPlace;
import ru.job4j.cinema.validation.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HallServlet extends HttpServlet {
    private ValidateService validator = ValidateService.getInstance();

    /**
     * Метод возвращает в ответе json объект с местами в зале и
     * количеством рядов и мест.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = this.getJsonHall();
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }

    /**
     * Метод получает в запросе данные формы (имя, номер пользователя, номер места),
     * бронирует место и возвращает в ответе результат бронирования.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormJson form = this.getForm(req, resp);
        String answer = this.bookSeat(form);
        String json = "{\"answer\" : \"" + answer + "\"}";
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8));
        pw.append(json);
        pw.flush();
    }

    /**
     * Метод для получения Json объекта с местами в зале и общим
     * количеством мест и рядов.
     */
    private String getJsonHall() throws IOException {
        List<HallPlace> places = new ArrayList<>(this.validator.findAll());
        HallJson hall = new HallJson(places, 3, 5);
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, hall);
        return sw.toString();
    }

    /**
     * Метод считывает из запроса данные формы и возвращает объект
     * FormJson с данными.
     */
    private FormJson getForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        String content = sb.toString();
        StringReader stringReader = new StringReader(content);

        ObjectMapper mapper = new ObjectMapper();
        FormJson form = mapper.readValue(stringReader, FormJson.class);
        return form;
    }

    /**
     * Метод для бронирования места.
     * Получает данные из объекта FormJson, передает их
     * в валидатор для бронирования места в базе данных.
     * Возвращает строку с результатом выполнения запроса
     * бронирования.
     */
    private String bookSeat(FormJson form) {
        String name = form.getUsername();
        String phone = form.getPhone();
        String id = form.getId();
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
