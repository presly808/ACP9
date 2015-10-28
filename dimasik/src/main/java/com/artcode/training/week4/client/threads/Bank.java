package com.artcode.training.week4.client.threads;

import java.util.LinkedList;
import java.util.List;

public class Bank {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        List<Thread> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ProducerThread(account, 10000));
            list.add(thread);
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ConsumerThread(account, 20000));
            list.add(thread);
            thread.start();
        }


        for (Thread thread : list) {
            thread.join(1500);
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

class Account {

    private Object monitor = new Object();
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
        cash -= money;
        notifyAll();
    }

    public synchronized void put(int money) {
        while (cash > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cash += money;
        notifyAll();
    }

    public int getCash() {
        return cash;
    }

}