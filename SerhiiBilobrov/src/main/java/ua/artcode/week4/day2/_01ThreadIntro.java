package ua.artcode.week4.day2;

/**
 * Created by serhii on 23.10.15.
 */
public class _01ThreadIntro {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            MyThread myThread = new MyThread(1000, 100);
            myThread.setDaemon(true);
            myThread.start();
        }

        Thread mainThread = Thread.currentThread();
        for (int i = 0; i < 1000; i++) {
            System.out.println(mainThread.getName() + " " + i);
        }
    }

}

class MyThread extends Thread {

    private int count;
    private int sleepInterval;

    public MyThread(int count, int sleepInterval) {
        this.count = count;
        this.sleepInterval = sleepInterval;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(getName()+ " " + i);
            try {
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

