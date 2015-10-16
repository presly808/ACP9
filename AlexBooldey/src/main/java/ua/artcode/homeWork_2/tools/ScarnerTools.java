package ua.artcode.homeWork_2.tools;

import java.util.Scanner;

public class ScarnerTools {
    public static String keyboardString(String message) {
        System.out.print(message + "$ : ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
