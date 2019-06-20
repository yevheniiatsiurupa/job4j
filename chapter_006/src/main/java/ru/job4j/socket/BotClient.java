package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 20/06/2019
 */

public class BotClient {
    private Socket socket;

    public BotClient(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод для запуска бота со стороны клиента.
     * Создаем сокет для общения с сервером.
     * Создаем потоки ввода и вывода из сервера.
     * Создаем сканер для считывания данных с консоли.
     * Считываем фразы из потока ввода, пока не будет введена пустая строка.
     */
    public void init() {
        try {
            System.out.println("Подключение состоялось.");
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            Scanner console = new Scanner(System.in);
            String str;
            String question;
            do {
                question = console.nextLine();
                out.println(question);
                str = in.readLine();
                while (!str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!("exit").equals(question));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5000);
        new BotClient(socket).init();
    }
}
