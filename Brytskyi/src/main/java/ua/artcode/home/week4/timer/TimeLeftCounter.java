package ua.artcode.home.week4.timer;

/**
 * User: huyti
 * Date: 28.10.2015
 */
public class TimeLeftCounter {
    long startTime;
    long timeMissed;

    public TimeLeftCounter() {
        this.startTime = System.currentTimeMillis()/1000;
        showSeconds();
    }

    private void showSeconds() {
        long checker = -1;
        while (!Thread.currentThread().isInterrupted()) {
            timeMissed = System.currentTimeMillis()/1000 - startTime;
            if (checker != timeMissed) System.out.println((timeMissed + 1) + " second left");
            checker = timeMissed;
        }
    }
}
