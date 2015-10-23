package ua.artcode.week4.day2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by serhii on 23.10.15.
 */
public class _01TestJoin {

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new LinkedList<>();

        List<ThreadWorker> workers = new LinkedList<>();
        for (int i = 0; i < 50; i++) {

            ThreadWorker t1 = new ThreadWorker();
            workers.add(t1);

            Thread thread = new Thread(t1);
            threads.add(thread);
            thread.start();
        }

        int amount = 0;

        // waits for ending
        for (Thread thread : threads) {
            thread.join();
        }

        for (ThreadWorker worker : workers) {
            amount += worker.getResult();
        }

        System.out.println(amount);

    }


}

class ThreadWorker implements Runnable {

    private int result;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            result += 1;
        }
    }

    public int getResult(){
        return result;
    }
}

