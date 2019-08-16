package ru.job4j.exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @version 1.0.
 * @since 12/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class SearchTask implements Runnable {
    /**
     * Поле содержит файл для поиска текста.
     */
    private File file;

    /**
     * Поле содержит текст для поиска в файле.
     */
    private String forSearch;

    public SearchTask(File file, String forSearch) {
        this.file = file;
        this.forSearch = forSearch;
    }

    /**
     * Метод считывает данные из файла и проверяет,
     * содержит ли он искомый текст.
     * Если содержит - выводится сообщение об этом.
     */
    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            StringBuilder sb = new StringBuilder();
            while (br.ready()) {
                sb.append(br.readLine());
                sb.append(System.lineSeparator());
            }
            String result = sb.toString();
            boolean found = result.contains(this.forSearch);
            if (found) {
                System.out.println("Text found in File: " + this.file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
