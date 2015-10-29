package ua.artcode.homeWork_4.asynchroChat;

import ua.artcode.utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Server {
    private final int PORT = 8888;
    private ServerSocket server;

    private BlockingDeque<String> messages;
    private Set<Connection> clients;
    Connection connection;

    {
        new Thread(new SendMessages()).start();
        messages = new LinkedBlockingDeque<>(1);
        clients = new HashSet<>();
    }

    public void start() throws IOException {

        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Can't connect to the port");
            System.exit(-1);
        }

        while (true) {
            System.out.println("Server waiting...");
            Socket client = server.accept();
            connection = new Connection(client);
            connection.start();
            clients.add(connection);
            System.out.println("Client " + client.getInetAddress() + " connect!");
        }
    }

    private class Connection extends Thread {
        private Socket client;

        private BufferedReader in;
        private PrintWriter out;
        private boolean state = true;

        public Connection(Socket socket) {
            this.client = socket;
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream());
            } catch (IOException ignore) {
                System.out.println((char) 27 + "[33mOoops!!");
            }
        }

        public void run() {
            try {
                while (state) {
                    out.println("Welcome to the chat!\nWrite your message...");
                    out.flush();
                    while (true) {
                        String message = in.readLine();
                        if (message.equalsIgnoreCase("quit")) {
                            state = false;
                            break;
                        }
                        messages.put(client.getInetAddress().toString() + ":" + message);
                    }
                }
            } catch (Exception e) {
                /*NOP*/
            } finally {
                try {
                    messages.put((char) 27 + "[32mClient " + client.getInetAddress() + " leave chatroom");
                } catch (InterruptedException ignore) {
                   /*NOP*/
                }
                close();
            }
        }

        private void close() {
            try {
                IOUtils.closeIn(client.getInputStream());
                IOUtils.closeOut(client.getOutputStream());
                client.close();
            } catch (IOException e) {
                System.out.println("Socket closed");
            }
        }
    }

    private class SendMessages implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String send = messages.take();
                    for (Connection client : clients) {
                        client.out.println(send);
                        client.out.flush();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
