package ru.job4j.examples;

import java.io.*;

public class DocReader {
    public static void main(String[] args) throws Exception {
        System.setProperty("console.encoding","utf-8");
        File file = new File("chapter_020/src/main/resources/text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("chapter_020/src/main/resources/read.txt")));
        while (br.ready()) {
           String a = br.readLine();
            System.out.println(a);
//           pw.println(a);
        }
        br.close();
//        pw.close();
    }
}
