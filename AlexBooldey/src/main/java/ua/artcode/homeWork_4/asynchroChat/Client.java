package ua.artcode.homeWork_4.asynchroChat;

import ua.artcode.utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final int PORT = 8888;
    private final String IP = "127.0.0.1";

    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader console;
    private Socket client;
    private boolean status = true;

    public void start() {
        connect();
        try {
            Thread receiveMessage = new Thread(new ReceiveMessageLogic());
            receiveMessage.start();

            out = new PrintWriter(client.getOutputStream());
            console = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                Thread.sleep(100);
                String writeMessage = console.readLine();
                out.println(writeMessage);
                out.flush();

                if (writeMessage.equalsIgnoreCase("quit")) {
                    status = false;
                    receiveMessage.interrupt();
                    break;
                }
            }
        } catch (Exception ignore) {/*NOP*/
        } finally {
            close();
        }
    }

    private void connect() {
        try {
            client = new Socket(IP, PORT);
        } catch (IOException e) {
            System.out.println((char) 27 + "[31mCan't connect to the port!");
            System.exit(-1);
        }
        System.out.println((char) 27 + "[34mConnected to the server!");
    }

    private void close() {
        try {
            IOUtils.closeIn(client.getInputStream());
            IOUtils.closeOut(client.getOutputStream());
        } catch (IOException e) {
            System.err.println("Socket closed");
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ReceiveMessageLogic implements Runnable {

        @Override
        public void run() {
            while (status) {
                try {
                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    String serverMessage = null;
                    try {
                        serverMessage = in.readLine();
                    } catch (IOException e) {
                     /*NOP*/
                    }
                    assert serverMessage != null;
                    if (serverMessage.equalsIgnoreCase("quit")) {
                        break;
                    }
                    System.out.println((char) 27 + "[36m" + serverMessage);
                }
            }
        }
    }
}
