package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 20/06/2019
 */

public class BotServer {
    private Socket socket;
    private Map<String, String> answersTable = new HashMap<>();

    public BotServer(Socket socket) {
        this.socket = socket;
        answersTable.put("hello", "Hello, dear friend, I'm oracle.");
        answersTable.put("How are you?", "I'm ok. Happy to see you!");
        answersTable.put("Tell me about my future", "You will marry next year and become extremely rich in ten years.");
        answersTable.put("exit", "Bye! See you next time!");
    }


    /**
     * Метод для запуска сервера.
     * Создаем серверный сокет, связанный с портом port. Этот сокет слушает порт.
     * Когда устанавливается соединение, то воссоздается клиентский сокет.
     * Создаем потоки ввода и вывода из сокета.
     * Читаем строку из потока ввода.
     * Отправляем ответную строку через поток вывода.
     * Конец сообщения обозначен отправкой пустой строки.
     * Для выхода пользователь вводит "exit".
     */
    public void init() {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String ask;
            do {
                System.out.println("wait command...");
                ask = in.readLine();
                System.out.println(ask);
                String answer = answersTable.get(ask) == null ? "It's too difficult question" : answersTable.get(ask);
                out.println(answer);
                out.println();
                out.flush();
            } while (!("exit".equals(ask)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(5000).accept()) {
            new BotServer(socket).init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
