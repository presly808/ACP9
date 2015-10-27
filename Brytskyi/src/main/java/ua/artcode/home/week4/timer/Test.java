package ua.artcode.home.week4.timer;

import java.util.Scanner;

/**
 * User: huyti
 * Date: 28.10.2015
 */
public class Test {
    public static void main(String[] args) {
        Timer timer = new Timer();
//        timer.start(TimeLeftCounter.class, 10000);
        timer.start(TimeLeftCounter.class);
        Scanner s = new Scanner(System.in);
        Thread th = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (s.nextLine().equals("stop")) {
                        timer.finish();
                        return;
                    }
                }
            }
        };
        th.start();

    }
}
