package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 16/06/2019
 */

public class Analyze {
    /**
     * Метод находит время, когда сервер не работал и записывает промежутки
     * в файл target.
     * @param source исходный файл.
     * @param target целевой файл.
     */
    public void unavailable(String source, String target) {
        List<String> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            out = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(target))) {
            String start = null;
            String end = null;
            boolean check = true;
            for (String tmp : out) {
                if (check
                        && (tmp.startsWith("400") || tmp.startsWith("500"))) {
                    start = tmp.substring(4);
                    check = false;
                    continue;
                }
                if (!check
                        && (tmp.startsWith("200") || tmp.startsWith("300"))) {
                    end = tmp.substring(4);
                    pw.println(start + ";" + end);
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
