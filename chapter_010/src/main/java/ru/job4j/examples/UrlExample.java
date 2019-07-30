package ru.job4j.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlExample {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("https://yandex.ru/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine = in.readLine();
        while (inputLine != null) {
            System.out.println(inputLine);
            inputLine = in.readLine();
        }

        in.close();
    }
}
