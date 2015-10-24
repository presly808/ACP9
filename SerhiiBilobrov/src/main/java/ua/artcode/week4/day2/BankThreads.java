package ua.artcode.week4.day2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by serhii on 23.10.15.
 */
public class BankThreads {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        List<Thread> list = new LinkedList<>();
        int stepsCount = 10000;

        int threadsCount = 10;

        for (int i = 0; i < threadsCount; i++) {
            Thread producer = new Thread(new ProducerThread(account, stepsCount));
            Thread consumer = new Thread(new ConsumerThread(account, stepsCount));

            list.add(consumer);
            list.add(producer);

            consumer.start();
            producer.start();
        }


        for (Thread thread : list) {
            thread.join();
        }

        System.out.println(account.getCash());

    }

}

class ConsumerThread implements Runnable {

    private Account account;
    private int count;

    public ConsumerThread(Account account, int count) {
        this.account = account;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            account.withdraw(1);
        }
    }
}


class ProducerThread implements Runnable {

    private Account account;
    private int count;

    public ProducerThread(Account account, int count) {
        this.account = account;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            account.put(1);
        }
    }
}

class Account {

    private int cash;

    public Account(int cash) {
        this.cash = cash;
    }

    public synchronized void withdraw(int money) { // this
        while (cash < money) {
            try {
                wait(); // go sleep and free monitor
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cash = cash - money;
        notifyAll();
    }

    public synchronized void put(int money) { // synchronized(this){}
        while (cash > 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cash = cash + money;
        notifyAll();
    }

    public int getCash() {
        return cash;
    }

}
