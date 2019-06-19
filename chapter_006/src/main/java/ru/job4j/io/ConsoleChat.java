package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleChat {
    /**
     * Путь к исходному файлу с фразами.
     */
    private String input;

    /**
     * Путь к файл, куда записывать диалог.
     */
    private File output;

    private static final String CONTINUED = "продолжить";
    private static final String STOP = "стоп";
    private Map<String, Boolean> answersTable = new HashMap<>();


    /**
     * Конструктор.
     * При создании объекта создает временный файл во временной директории.
     * В этот файл будет записан диалог.
     * @param input путь к файлу с фразами.
     */
    public ConsoleChat(String input) throws Exception {
        this.input = input;
        String prefix = "logConsoleChat";
        String suffix = ".txt";
        this.output = File.createTempFile(prefix, suffix,
                new File(System.getProperty("java.io.tmpdir")));
        answersTable.put("закончить", true);
    }

    /**
     * Метод возвращает список строк из файла.
     * @param source исходный файл для чтения.
     * @return возвращает список строк.
     */
    public List<String> lines(String source) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            br.lines().forEach(result :: add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод возвращает произвольную строку из списка.
     * @param input список строк.
     */
    public String randomLine(List<String> input) {
        int index = (int) (Math.random() * input.size());
        return input.get(index);
    }

    public void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(new FileOutputStream(this.output.getAbsolutePath()))) {
            List<String> consoleAnswers = this.lines(this.input);
            String answer;
            String consoleReply;
            boolean exit;
            boolean stopped = false;
            do {
                answer = br.readLine();
                pw.println(answer);
                exit = answersTable.get(answer) != null;
                if (exit) {
                    continue;
                }
                if (STOP.equals(answer)) {
                    stopped = true;
                    continue;
                }
                if (CONTINUED.equals(answer)) {
                    stopped = false;
                }
                if (!stopped) {
                    consoleReply = this.randomLine(consoleAnswers);
                    pw.println(consoleReply);
                    System.out.println(consoleReply);
                }
            } while (!exit);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String inputPath = "./ConsolePhrases.txt";
        new ConsoleChat(inputPath).init();
    }
}
