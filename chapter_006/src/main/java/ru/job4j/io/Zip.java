package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 17/06/2019
 */
public class Zip {
    /**
     * Метод добавляет файл в файл архива.
     * Порядок добавления: создаем объект zip типа ZipOutputStream, в качестве параметра - поток вывода
     * BufferedOutputStream в файл target.
     * Создаем объект ZipEntry для файла source.
     * Добавляем его в zip с помощью putNextEntry.
     * Создаем поток ввода из файла (out). Считываем все байты из файла источника (out.readAllBytes()).
     * Записываем считанные байты в файл zip.
     * Закрытие файла не требуется, так как используется блок try with resources.
     * @param source файл, который добавляется в архив.
     * @param target файл архива.
     */
    public void pack(File source, File target, String ext) {
        List<File> sources = this.seekBy(source.getAbsolutePath(), ext);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File tmp : sources) {
                zip.putNextEntry(new ZipEntry(
                        tmp.getPath().substring(tmp.getPath().indexOf(source.getName()))
                ));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(tmp))) {
                    zip.write(out.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод возвращает список файлов, которые находятся в root и НЕ имеют расширения ext.
     * @param root исходная директория.
     * @param ext искомое расширение.
     * @return возвращает список файлов с расширением исключая ext.
     */
    public List<File> seekBy(String root, String ext) {
        Search tmp = new Search();
        List<String> exts = new ArrayList<>(Collections.singletonList(ext));
        return tmp.files(root, exts, false);
    }

    public static void main(String[] args) throws Exception {
        Zip testZip = new Zip();
        Args testArg = new Args(args);
        File dir = new File(testArg.directory());
        String ext = testArg.exclude();
        String out = testArg.output();
        File targetZip = new File(out);
        testZip.pack(dir, targetZip, ext);
    }
}
