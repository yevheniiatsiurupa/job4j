package ru.job4j.servlets.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CityServlet extends HttpServlet {
    private final Map<String, List<String>> cities = new ConcurrentHashMap<>();
    public CityServlet() {
        cities.put("Russia", Arrays.asList("Moscow", "Saint-Petersburg", "Novgorod"));
        cities.put("Ukraine", Arrays.asList("Kyiv", "Kharkiv"));
        cities.put("Belarus", Arrays.asList("Minsk", "Gomel"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> countries = new ArrayList<>(this.cities.keySet());
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(countries);
        String json = "{\"countries\" : " + result + "}";
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String country = reader.readLine();
        List<String> cityList = this.cities.get(country);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(cityList);
        String json = "{\"cities\" : " + result + "}";
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }
}
