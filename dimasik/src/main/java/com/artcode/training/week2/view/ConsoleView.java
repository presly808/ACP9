package com.artcode.training.week2.view;

import com.artcode.training.week2.controller.CommandController;

public class ConsoleView {
    public static void main(String[] args) {
        CommandController commandController = new CommandController(System.in);
        while (true) {
            System.out.print(commandController.getCurrentLocation());
            String result = commandController.readInputCommand();
            if (!result.isEmpty()) {
                System.out.println(result);
            }
        }
    }
}
