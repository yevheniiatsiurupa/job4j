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

public class JsonServlet extends HttpServlet {
    private MemoryStorageJson storage = MemoryStorageJson.getInstance();

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
            resp.setContentType("text/json");
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            pw.append(content);
            pw.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
