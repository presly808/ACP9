package ua.artcode.homeWork_3.timer;

import java.util.Scanner;

public class TestTimer {

    public static void main(String[] args) {
        MyTimer timer = new MyTimer();

        timer.start(1000, new Action() {
            @Override
            public void run() {
                System.out.println("~in method run");

            }
        });

        Scanner scan = new Scanner(System.in);
        if (scan.nextLine().equals(".")) {
            timer.stop();
        }
    }
}
