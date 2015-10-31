package com.artcode.training.week4.client;

import com.artcode.training.week2.controller.CommandController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoteCommandThread implements Runnable {
    public static final String EXECUTE_COMMAND_KEYWORD = "run command";
    private final Scanner scanner;
    private static List<String> outputInfo = new ArrayList<>();
    private static final Object outputInfoMonitor = new Object();

    public RemoteCommandThread(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        CommandController commandController = new CommandController(scanner);
        synchronized (outputInfoMonitor) {
            outputInfo.add(commandController.getCurrentLocation());
            outputInfoMonitor.notifyAll();
        }
        String result = commandController.readInputCommand();
        int timeout = scanner.nextInt();
        if (timeout > 0) try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!result.isEmpty()) {
            synchronized (outputInfoMonitor) {
                outputInfo.add(result);
                outputInfoMonitor.notifyAll();
            }
        }
    }

    public static String getOutputInfo() {
        return outputInfo.remove(0);
    }

    public static boolean hasInfoToPrint() {
        return !outputInfo.isEmpty();
    }

    public static Object getOutputInfoMonitor() {
        return outputInfoMonitor;
    }
}
