package ua.artcode.week4.day2;

/**
 * Created by serhii on 23.10.15.
 */
public class _04ThreadStates {

    public static void main(String[] args) throws InterruptedException {
        Thread target = new Thread(new ChangeStateThead(Thread.currentThread()));

        Thread monitorThread = new Thread(new MonitorThread(target));

        monitorThread.start();

        Thread.sleep(100);
        target.start();
        Thread.sleep(1000);
    }



}

class MonitorThread implements Runnable {

    private Thread target;

    public MonitorThread(Thread target) {
        this.target = target;
    }

    @Override
    public void run() {
        while(true){
            System.out.printf("%s, %s\n", target.getName(), target.getState());

            if(target.getState() == Thread.State.TERMINATED){
                System.out.printf("%s, %s\n", target.getName(), target.getState());
                break;
            }
        }
    }
}

class ChangeStateThead implements Runnable {

    private Thread forWaitThread;

    public ChangeStateThead(Thread forWaitThread) {
        this.forWaitThread = forWaitThread;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            forWaitThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TERMINATED
    }
}

