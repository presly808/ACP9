package com.artcode.training.week3.xml.view;

import com.artcode.myproject.model.RentalRequirements;
import com.artcode.myproject.model.User;
import com.artcode.training.week3.xml.controller.XMLController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    public static final String INTRO_TEXT = "Please enter 1 for writing to XML file or other number for reading";
    public static final String INSTRUCTIONS_FOR_WRITING = "Please, enter path to file for writing";
    public static final String INSTRUCTIONS_FOR_READING = "Please, enter path to file for reading";

    public static void main(String[] args) throws IOException {
        showIntro();
        Scanner scanner = new Scanner(System.in);
        boolean command = scanner.nextInt() == 1;
        showInstructions(command);
        Object result = XMLController.executeCommand(command, scanner.next(), command ? prepareDemoData() : null);
    }

    private static Object prepareDemoData() {
        List<User> users = new ArrayList<>();
//        users.add(new User("user1", "pass1", new RentalRequirements(1000, "text1"), 11));
//        users.add(new User("user2", "pass2", new RentalRequirements(2000, "text2"), 22));
//        users.add(new User("user3", "pass3", new RentalRequirements(3000, "text3"), 33));
//        users.add(new User("user4", "pass4", new RentalRequirements(4000, "text4"), 44));
//        users.add(new User("user5", "pass5", new RentalRequirements(5000, "text5"), 55));
        return users;
    }

    private static void showIntro() {
        System.out.println(INTRO_TEXT);
    }

    private static void showInstructions(boolean write) {
        System.out.println(write ? INSTRUCTIONS_FOR_WRITING : INSTRUCTIONS_FOR_READING);
    }
}
