package ua.artcode.home.week4.timer;

/**
 * User: huyti
 * Date: 27.10.2015
 */
public class Timer {
    long timeMilis;
    Thread th;

    public void start(Class task) {
        th = new Thread(new Runner(task));
        th.start();
    }

    public void start(Class task, long time) {
        timeMilis = time;
        th = new Thread(new Runner(task));
        th.start();
        long startTime = System.currentTimeMillis();
        long missedTime;
        while (true) {
            missedTime = System.currentTimeMillis() - startTime;
            if (missedTime >= timeMilis) {
                th.interrupt();
                th.isInterrupted();
                System.out.println("Task is stopped");
                return;
            }
        }
    }

    public void finish() {
        th.interrupt();
    }


}

class Runner implements Runnable {
    Class task;

    public Runner(Class task) {
        this.task = task;
    }

    @Override
    public void run() {
        try {
            task.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
