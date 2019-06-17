package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Example {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("chapter_006\\src\\main\\java\\ru\\job4j\\io\\resources\\Example.txt"))) {
            String one = "first string";
            String two = "second string";
            String three = "third string";
            pw.println(one);
            pw.println(two);
            pw.println(three);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
