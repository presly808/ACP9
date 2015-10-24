package ua.artcode.week4.day1.client;

import ua.artcode.week2.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by serhii on 22.10.15.
 */
public class TestSimpleClient {


    public static void main(String[] args) throws IOException {
        // localhost -> 127.0.0.1, my ip 192.168.1.39
        Socket socket = new Socket("localhost", 8888);
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
            while(true){
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
        Scanner sc= new Scanner(inputStream);
        while (true){
            System.out.println(clientInfo + ":" + (sc.hasNextLine() ? sc.nextLine() : ""));
        }
    }
}
