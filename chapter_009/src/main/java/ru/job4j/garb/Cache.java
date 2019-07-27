package ru.job4j.garb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @version 1.0.
 * @since 27/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class Cache {
    /**
     * Поле содержит хранилище кэша.
     * Ключ - имя файла, значение - текст файла (с soft reference).
     */
    private HashMap<String, SoftReference<String>> cache = new HashMap<>();

    /**
     * Метод для получения содержимого текстового файла по имени файла.
     * В случае если файл с таким именем имеется в хранилище кэша, то содержимое считывается
     * из кэша.
     * Если файл с таким именем не найден в кэше или если содержимое по ключу равно нулю (такое возможно
     * после garbage collection), то считываем содержимое файла с заданным именем и адресом из ресурсов и
     * добавляем пару ключ - значение в хранилище кэша для будущих поисков.
     * @param key ключ имя файла.
     * @param path путь к файлу.
     * @return возвращает содержимое текстового файла.
     */
    public String getValue(String key, String path) throws FileNotFoundException {
        String result;
        if (cache.get(key) != null && cache.get(key).get() != null) {
            System.out.println("Read from cache:");
            result = cache.get(key).get();
        } else {
            System.out.println("Read from resources:");
            StringJoiner out = new StringJoiner(System.lineSeparator());
            BufferedReader br = new BufferedReader(new FileReader(new File(path, key)));
            br.lines().forEach(out::add);
            result = out.toString();
            cache.put(key, new SoftReference<>(result));
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = new File("chapter_009/src/main/resources").getAbsolutePath();
        Cache cache = new Cache();

        String names = cache.getValue("Names.txt", path);
        System.out.println(names);
        String namesFromCache = cache.getValue("Names.txt", path);
        System.out.println(namesFromCache);
    }
}
