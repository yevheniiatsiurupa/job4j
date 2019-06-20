package ru.job4j.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int servPort = 5000;
        String address = "192.168.0.105";
        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            System.out.println("Подключение к серверу...");
            Socket socket = new Socket(inetAddress, servPort);
            InputStream socketInStr = socket.getInputStream();
            OutputStream socketOutStr = socket.getOutputStream();
            DataInputStream in = new DataInputStream(socketInStr);
            DataOutputStream out = new DataOutputStream(socketOutStr);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ваше сообщение: ");

            String str = null;
            while (true) {
                str = br.readLine();
                out.writeUTF(str);
                out.flush();
                str = in.readUTF();
                System.out.println("Ответ сервера: " + str);
                System.out.println("Ваше сообщение: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
