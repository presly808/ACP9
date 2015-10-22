package ua.artcode.homeWork_3.timer;

public class MyTimer {

    private Thread timerThread;

    public void start(long period, Action action) {
        timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long currentTime;

                while (!timerThread.isInterrupted()) {
                    currentTime = System.currentTimeMillis();
                    if ((currentTime - startTime) >= period) {
                        action.run();
                        startTime = currentTime;
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("Timer stopped");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        timerThread.start();
    }

    public void stop() {
        timerThread.interrupt();
    }
}
