package ua.artcode.week4;

import java.util.LinkedList;
import java.util.List;

public class TreadTest {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        List<Thread> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ProducerThread(account, 10000));
            Thread thread1 = new Thread(new Girlfriend(account, 10000));

            list.add(thread);
            list.add(thread1);

            thread.start();
            thread1.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println(account.getCash());
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

class Girlfriend implements Runnable {

    private Account account;
    private int count;

    public Girlfriend(Account account, int limit) {
        this.account = account;
        this.count = limit;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            account.withdraw(1);
        }
    }

}

class Account {
    private int cash;

    public Account(int cash) {
        this.cash = cash;
    }

    public synchronized void withdraw(int money) {
        while (cash < money) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cash = cash - money;
    }

    public synchronized void put(int money) {

        cash = cash + money;
        notifyAll();
    }

    public int getCash() {
        return cash;
    }

}

