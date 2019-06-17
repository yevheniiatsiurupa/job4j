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
     * @param include true если нужен список файлов с расширением, false если исключая расширение.
     * @return возвращает список файлов с искомыми расширениями из директории.
     */
    public List<File> files(String parent, List<String> exts, boolean include) {
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
                        if ((include && this.checkExts(tmp, exts))
                        || (!include && !this.checkExts(tmp, exts))) {
                            result.add(tmp);
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean checkExts(File file, List<String> exts) {
        String fileName = file.getName();
        String fileExt = fileName.substring(fileName.indexOf(".") + 1);
        return exts.contains(fileExt);
    }


}
