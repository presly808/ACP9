package com.artcode.training.week2.controller;

import com.artcode.training.week2.commands.Commands;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class CommandController {

    public static final String SEPARATOR = " ";
    public static final String QUIT = "q";
    private File currentFile;
    private Scanner scanner;

    public CommandController(InputStream inputStream) {
        this.currentFile = new File("").getAbsoluteFile();
        this.scanner = new Scanner(inputStream);
    }

    public String readInputCommand() {
        try {
            String input = scanner.nextLine();
            if (QUIT.equals(input)) System.exit(0);
            String[] args = input.split(SEPARATOR);
            Commands command = Commands.valueOf(args[0].toUpperCase());
            return precessCommand(args, command);
        } catch (Exception ignored) {
            return "";
        }
    }

    private String precessCommand(String[] args, Commands command) {
        String result;
        if (command.equals(Commands.HELP)) {
            result = command.getCommand().execute(currentFile, Arrays.stream(Commands.values()).filter(commands1 -> !commands1.equals(Commands.HELP))
                    .map(commands -> commands.getCommand().help()).toArray(String[]::new));
        } else {
            result = command.getCommand().run(currentFile, Arrays.stream(args).skip(1).toArray(String[]::new));
        }
        currentFile = command.getCommand().getNewCurrentFile();
        return result;
    }

    public String getCurrentLocation() {
        return currentFile.getAbsolutePath() + ">";
    }
}
