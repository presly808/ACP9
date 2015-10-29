package ua.artcode.week4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) throws IOException {
        // localhost -> 127.0.0.1, my ip 192.168.1.39
        Socket socket = new Socket("192.168.1.39", 8888);
        new Thread(new ReadClientMessageLogic(
                socket.getInputStream(),
                socket.getInetAddress().toString())).start();

        new Thread(new WriteMessageLogic(socket)).start();

    }
}

class WriteMessageLogic implements Runnable {

    private Socket socket;
    private Scanner console;

    public WriteMessageLogic(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            console = new Scanner(System.in);
            while (true) {
                System.out.println("Input your message");
                pw.println(console.nextLine());
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class ReadClientMessageLogic implements Runnable {

    private InputStream inputStream;
    private String clientInfo;

    public ReadClientMessageLogic(InputStream inputStream, String clientInfo) {
        this.inputStream = inputStream;
        this.clientInfo = clientInfo;
    }

    @Override
    public void run() { // logic of thread
        Scanner sc = new Scanner(inputStream);
        while (true) {
            System.out.println(clientInfo + ":" + (sc.hasNextLine() ? sc.nextLine() : ""));
        }
    }
}
