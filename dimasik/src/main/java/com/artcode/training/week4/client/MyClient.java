package com.artcode.training.week4.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.39", 8888);


        new Thread(new ChatClientWriter(socket)).start();
        new Thread(new ChatClientReader(socket.getInputStream(), socket.getInetAddress().toString())).start();
    }
}

class ChatClientWriter implements Runnable {
    private Socket socket;

    public ChatClientWriter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String output = null;
                synchronized (RemoteCommandThread.getOutputInfoMonitor()) {
                    if (RemoteCommandThread.hasInfoToPrint()) {
                        output = RemoteCommandThread.getOutputInfo();
                    }
                }
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                output = output == null ? new Scanner(System.in).nextLine() : output;
                printWriter.println(output);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatClientReader implements Runnable {
    private InputStream inputStream;
    private String clientInfo;

    public ChatClientReader(InputStream inputStream, String clientInfo) {
        this.inputStream = inputStream;
        this.clientInfo = clientInfo;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(inputStream);
        while (true) {
            String inputText = sc.hasNextLine() ? sc.nextLine() : "";
            if (RemoteCommandThread.EXECUTE_COMMAND_KEYWORD.equals(inputText)) {
                new Thread(new RemoteCommandThread(sc)).start();
            }
            System.out.println(clientInfo + ":" + inputText);
        }
    }
}
