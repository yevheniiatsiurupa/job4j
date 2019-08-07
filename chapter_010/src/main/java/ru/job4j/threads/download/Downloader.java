package ru.job4j.threads.download;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @version 1.0.
 * @since 30/07/2019.
 * @author Evgeniya Tsiurupa
 */

public class Downloader implements Runnable {
    /**
     * Заданный адрес URL.
     */
    private String url;

    /**
     * Ограничение скорости скачивания в кБ / сек.
     */
    private long speed;

    /**
     * Файл для записи скачанного файла.
     */
    private File target;

    public Downloader(String url, long speed, File target) {
        this.url = url;
        this.speed = speed;
        this.target = target;
    }

    /**
     * Основной метод для скачивания файла.
     * Открываем соединение с адресом URL.
     * Открываем поток для скачивания файла.
     * Открываем поток для записи в целевой файл.
     * Создаем и запускаем отдельную нить t для процесса скачивания.
     * Как только процесс скачивания закончится, то нить curr (основная) прерывается.
     * В основной нити запускается цикл, который останавливает поток на секунду,
     * потом проверяет предыдущее и текущее значение размера файла,
     * находит разность (скачанный объем) и сравнивает ее с заданным ограничением скорости.
     * При превышении скорости скачивания устанавливается пауза нужного размера
     * в потоке t (скачивание приостанавливается).
     */
    @Override
    public void run() {
        try {
            URL ur = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) ur.openConnection();
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(this.target));
            long read = 0;
            long prevTime = System.currentTimeMillis();
            byte[] buffer = new byte[4096];
            int c = bis.read(buffer);
            while (c != -1) {
                read += c;
                bos.write(buffer, 0, c);
                c = bis.read(buffer);
                if (read > speed * 1000) {
                    System.out.println(String.format("Downloaded for %d millisecond: %d",
                            (System.currentTimeMillis() - prevTime), read / 1000));
                    if ((System.currentTimeMillis() - prevTime) < 1000L) {
                        Thread.sleep(1000L - (System.currentTimeMillis() - prevTime));
                        System.out.println("пауза");
                    }
                    read = 0;
                    prevTime = System.currentTimeMillis();
                }
            }
            System.out.println("File was downloaded.");
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "https://courses.cs.ut.ee/MTAT.03.279/2016_fall/uploads/Main/jmm-lecture-v3.pdf";
        long speed = 200;
        File target = new File("chapter_010/src/main/resources/abc.pdf");
        target.createNewFile();
        Downloader first = new Downloader(url, speed, target);
        new Thread(first).start();
    }
}
