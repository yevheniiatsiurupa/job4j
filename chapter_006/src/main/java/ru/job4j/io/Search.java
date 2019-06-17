package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 17/06/2019
 */

public class Search {
    /**
     * Метод возвращает список файлов с конкретным расширением.
     * @param parent путь к папке, в которой ведется поиск.
     * @param exts список искомых расширений.
     * @return возвращает список файлов с искомыми расширениями из директории.
     */
    public List<File> files(String parent, List<String> exts) {
        File file = new File(parent);
        Queue<File> checkExts = new LinkedList<>();
        checkExts.offer(file);
        List<File> result = new ArrayList<>();
        while (!checkExts.isEmpty()) {
            File[] allFiles = checkExts.poll().listFiles();
            if (allFiles != null) {
                for (File tmp : allFiles) {
                    if (tmp.isDirectory()) {
                        checkExts.offer(tmp);
                    } else {
                        String fileName = tmp.getName();
                        String fileExt = fileName.substring(fileName.indexOf(".") + 1);
                        if (exts.contains(fileExt)) {
                            result.add(tmp);
                        }
                    }
                }
            }
        }
        return result;
    }
}
