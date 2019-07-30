package ru.job4j.threads;

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
            long prevSize = this.target.length();
            Thread curr = Thread.currentThread();

            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        int c = bis.read();
                        while (c != -1) {
                            bos.write(c);
                            c = bis.read();
                        }
                        curr.interrupt();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            t.start();

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    long size = this.target.length();
                    long diff = size - prevSize;
                    System.out.println("Downloaded for 1 second: " + diff / 1000);
                    if (diff > speed * 1000) {
                        long pause = diff / (speed * 1000);
                        t.sleep(pause * 1000);
                        System.out.println("пауза");
                    }
                    prevSize = size;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }

            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://jeisson.ecci.ucr.ac.cr/tmp/books/java/Java%20How%20to%20Program,%2010ed%20(early%20objects)%20-%20P.J.Deitel,%20H.M.Deitel%20%5B2014%5D.pdf";
        long speed = 200;
        File target = new File("chapter_010/src/main/resources/abc.pdf");
        target.createNewFile();
        Downloader first = new Downloader(url, speed, target);
        new Thread(first).start();
    }
}
