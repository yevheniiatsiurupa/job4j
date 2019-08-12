package ru.job4j.threads.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0.
 * @since 12/07/2019.
 * @author Evgeniya Tsiurupa
 */
public class DownloadGroup {
    private File source;

    public DownloadGroup(File source) {
        this.source = source;
    }

    public List<String> getURL() {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.source))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        File source = new File("chapter_010/src/main/resources/filesList.txt");
        File target = new File("chapter_010/src/main/resources/");
        DownloadGroup downloadGroup = new DownloadGroup(source);
        ExecutorService executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        long speed = 200;
        List<String> urls = downloadGroup.getURL();
        for (String tmp : urls) {
            String[] array = tmp.split("/");
            String name = array[array.length - 1];
            StringBuilder sb = new StringBuilder(target.getAbsolutePath());
            sb.append("\\");
            sb.append(name);
            String path = sb.toString();
            File file = new File(path);
            file.createNewFile();
            executor.submit(new Downloader(tmp, speed, file));
        }
        executor.shutdown();
    }
}
