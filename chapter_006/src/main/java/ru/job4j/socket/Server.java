package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 5000;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Ждем подключения к серверу...");
            Socket socket = serverSocket.accept();
            System.out.println("Подключение состоялось.");
            InputStream socketInStr = socket.getInputStream();
            OutputStream socketOutStr = socket.getOutputStream();
            DataInputStream in = new DataInputStream(socketInStr);
            DataOutputStream out = new DataOutputStream(socketOutStr);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String string = null;
            while (true) {
                string = in.readUTF();
                System.out.println("Получено сообщение: " + string);
                System.out.println("Ваше сообщение: ");
                string = br.readLine();
                out.writeUTF(string);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
