package ru.job4j.servlets.servlet;

import ru.job4j.servlets.models.UserJson;
import ru.job4j.servlets.storage.MemoryStorageJson;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Collection;

public class JsonServlet extends HttpServlet {
    private MemoryStorageJson storage = MemoryStorageJson.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<UserJson> users = this.storage.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(users);
        String json = "{\"users\" : " + result + "}";
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        String content = sb.toString();
        StringReader stringReader = new StringReader(content);
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserJson user = mapper.readValue(stringReader, UserJson.class);
            storage.add(user);
            this.doGet(req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
