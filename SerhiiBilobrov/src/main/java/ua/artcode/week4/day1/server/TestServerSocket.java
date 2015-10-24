package ua.artcode.week4.day1.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class TestServerSocket {

    private static int count = 0;

    public static void main(String[] args)  {
        ClientConnectionContainer container = new ClientConnectionContainer();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Server started!!!");

        while(true){
            try{
                Socket clientSocket = serverSocket.accept();// blocking method
                container.register(clientSocket.getOutputStream());

                Runnable clientThreadLogic =
                        new ReadClientMessageLogic(
                                clientSocket.getInputStream(),
                                clientSocket.getInetAddress().toString(),container);

                new Thread(clientThreadLogic).start(); // start new thread

                /*PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());

                String message = String.format("%d Hello from server, your ip %s, date %tc",
                        count++,clientSocket.getInetAddress(), new Date());

                // write to client
                pw.println(message);
                pw.flush();*/

                //System.out.println(clientSocket.getInetAddress() + ":" + (sc.hasNextLine() ? sc.nextLine(): ""));
                // read from client
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}

class ClientConnectionContainer {

    private List<PrintWriter> printWriters = new LinkedList<>();
    private int count = 0;

    public void register(OutputStream os){
        printWriters.add(new PrintWriter(os));
    }

    public void notifyAll(String message){
        count = count + 1;
        for (PrintWriter printWriter : printWriters) {
            printWriter.println(message);
            printWriter.flush();
        }
    }



}

class ReadClientMessageLogic implements Runnable {

    private ClientConnectionContainer container;
    private InputStream inputStream;
    private String clientInfo;

    public ReadClientMessageLogic(InputStream inputStream, String clientInfo, ClientConnectionContainer container) {
        this.inputStream = inputStream;
        this.clientInfo = clientInfo;
        this.container = container;
    }

    @Override
    public void run() { // logic of thread
        Scanner sc= new Scanner(inputStream);
        while (true){
            try{
                String clientMessage = clientInfo + ":" + sc.nextLine();
                System.out.println(clientMessage);
                container.notifyAll(clientMessage);
            } catch (NoSuchElementException e){
                // e.printStackTrace();
            }
        }
    }
}
