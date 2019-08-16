package ru.job4j.exam;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0.
 * @since 07/08/2019.
 * @author Evgeniya Tsiurupa
 */

public class SearchText {

    public List<File> getFiles(String source) {
        File target = new File(source);
        List<File> result = new ArrayList<>();
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
                result.add(tmp);
            }
        }
        return result;
    }

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
        List<File> files = new SearchText().getFiles(path);
        for (File tmp : files) {
            String name = tmp.getName();
            if (name.endsWith(".txt")) {
                executor.submit(new SearchTask(tmp, forSearch));
            }
        }
        executor.shutdown();
    }
}
