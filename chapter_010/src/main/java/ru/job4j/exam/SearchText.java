package ru.job4j.exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0.
 * @since 07/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class SearchText {
    /**
     * Метод для поиска заданного текста в файловой системе.
     * Создается пул потоков по количеству свободных процессоров.
     * Основной поток делает обход файловой системы (через добавление файлов и папок в очередь).
     * Если извлекаемый из очереди файл не является директорией,
     * то проверяется, что он текстовый.
     * Если файл текстовый, то в нем производится поиск текста.
     * Процесс поиска текста в файле задается как задание для executor,
     * чтобы один из свободных потоков произвел поиск.
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        String forSearch = "Search text";
        String path = "chapter_010/src/main/resources";

        File target = new File(path);
        Queue<File> queue = new LinkedList<>();
        queue.offer(target);
        while (!queue.isEmpty()) {
            File tmp = queue.poll();
            if (tmp.isDirectory()) {
                File[] files = tmp.listFiles();
                if (files != null) {
                    for (File f : files) {
                        queue.offer(f);
                    }
                }
            } else {
                String name = tmp.getName();
                if (name.endsWith(".txt")) {
                    Runnable task = new Runnable() {
                        @Override
                        public void run() {
                            try (BufferedReader br = new BufferedReader(new FileReader(tmp))) {
                                StringBuilder sb = new StringBuilder();
                                while (br.ready()) {
                                    sb.append(br.readLine());
                                    sb.append(System.lineSeparator());
                                }
                                String result = sb.toString();
                                boolean found = result.contains(forSearch);
                                if (found) {
                                    System.out.println("Text found in File: " + tmp.getName());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    executor.submit(task);
                }
            }
        }
        executor.shutdown();
    }
}
