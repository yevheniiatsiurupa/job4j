package ru.job4j.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 21/06/2019
 */

public class FileSearch {
    /**
     * Метод для поиска файла в заданной папке и записи найденных путей в файл.
     * @param root место поиска файла.
     * @param searchName карта со значениями для поиска (тип поиска - имя поиска).
     * @param output файл для записи результата.
     */
    public void init(String root, String searchName, String typeSearch, File output) {
        File rootFile = new File(root);
        Queue<File> checkQueue = new LinkedList<>();
        List<File> result = new ArrayList<>();
        checkQueue.offer(rootFile);
        while (!checkQueue.isEmpty()) {
            File[] list = checkQueue.poll().listFiles();
            if (list != null) {
                for (File tmp : list) {
                    if (tmp.isDirectory()) {
                        checkQueue.offer(tmp);
                    } else {
                        if (this.checkName(tmp, searchName, typeSearch)) {
                            result.add(tmp);
                        }
                    }
                }
            }
        }
        this.writeResult(result, output);
    }

    /**
     * Метод сравнивает имя файла с искомым именем, используя тип поиска.
     * Возможные типы поиска:
     * -f поиск полного совпадения имени,
     * -m поиск совпадения по маске.
     * @param file файл, имя которого сравниваем.
     * @param searchName искомое имя.
     * @param typeSearch тип поиска
     * @return возвращает соответствует ли имя поиску.
     */
    public boolean checkName(File file, String searchName, String typeSearch) {
        String fileName = file.getName();
        if ("-f".equals(typeSearch)) {
            return fileName.equals(searchName);
        }
        if ("-m".equals(typeSearch)) {
            if (searchName.contains("*")) {
                String[] str = searchName.split("\\*");
                boolean result = false;
                if (str.length == 2) {
                    result = fileName.startsWith(str[0]) && fileName.endsWith(str[1]);
                }
                if (str.length == 1) {
                    if (searchName.startsWith(str[0])) {
                        result = fileName.startsWith(str[0]);
                    } else {
                        result = fileName.endsWith(str[0]);
                    }
                }
                return result;
            }
        }
        return false;
    }

    /**
     * Метод для записи путей файлов из списка в текстовый файл.
     * @param result список файлов для записи.
     * @param output файл для записи результата.
     */
    public void writeResult(List<File> result, File output) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(output))) {
            for (File tmp : result) {
                pw.println(tmp.getAbsolutePath());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args ar = new Args(args);
        String root;
        String searchName;
        String typeSearch;
        String outputName;
        try {
            root = ar.directory();
            searchName = ar.name();
            typeSearch = ar.filterType();
            outputName = ar.output();
            File out = new File(outputName);
            new FileSearch().init(root, searchName, typeSearch, out);
        } catch (InvalidInputException iie) {
            StringBuilder sb = new StringBuilder();
            String ln = System.lineSeparator();
            sb.append("Некорректные данные. Данные должны содержать ключи:");
            sb.append(ln);
            sb.append("-d имя папки для поиска;");
            sb.append(ln);
            sb.append("-n имя искомого файла;");
            sb.append(ln);
            sb.append("-f искать полное совпадение или -m искать по маске;");
            sb.append(ln);
            sb.append("-o имя файла для записи результата поиска.");
            System.out.println(sb.toString());
        }
    }
}
