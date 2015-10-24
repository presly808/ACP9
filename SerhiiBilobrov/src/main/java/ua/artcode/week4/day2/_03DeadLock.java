package ua.artcode.week4.day2;

/**
 * Created by serhii on 23.10.15.
 */
public class _03DeadLock {


    public static void main(String[] args) throws InterruptedException {

        /*Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("some");
            }
        });
        thread.start();


        thread.join();*/


        Thread t1 = new Thread(new MyLogic(Thread.currentThread()));
        t1.start();
        t1.join(1000);

        System.out.println("End");


    }
}

class MyLogic implements Runnable {

    private Thread thread;

    public MyLogic(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
