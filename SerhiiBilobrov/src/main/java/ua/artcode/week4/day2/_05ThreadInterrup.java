package ua.artcode.week4.day2;

/**
 * Created by serhii on 23.10.15.
 */
public class _05ThreadInterrup {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            Thread own = Thread.currentThread();
            while (!own.isInterrupted()) {
                System.out.println(own.getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    own.interrupt();
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        // thread.setUncaughtExceptionHandler();

        Thread.sleep(1000);
        thread.interrupt();
    }


}


