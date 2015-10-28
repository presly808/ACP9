package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FindFileByNameUsingQueueCommand extends AbstractCommand {
    public static final int DEFAULT_THREADS_AMOUNT = 10;
    public int threadsAmount;
    private String filename;
    private List<String> result = new ArrayList<>();
    private Queue<File> directories = new PriorityQueue<>();
    private final Object resultMonitor = new Object();
    private final Object directoriesMonitor = new Object();

    @Override
    public String help() {
        return "find <file name> <threads limit> - search for file using limited amount of threads\n"
                + "find <file name> - search for file";
    }

    @Override
    public String execute(File file, String... args) {
        this.filename = args[0];
        if (args.length > 1) {
            threadsAmount = Integer.parseInt(args[1]);
        } else {
            threadsAmount = DEFAULT_THREADS_AMOUNT;
        }
        directories.add(file);
        Thread searchThread = new Thread(new SearchThreadsProducer());
        searchThread.start();
        try {
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.join("\n", result);
    }

    class SearchThreadsProducer implements Runnable {
        @Override
        public void run() {
            List<Thread> searchingThreads = new ArrayList<>();
            while (!directories.isEmpty() && !searchingThreads.isEmpty()) {
                if (searchingThreads.size() < threadsAmount) {
                    Thread searchingThread;
                    synchronized (directoriesMonitor) {
                        searchingThread = new Thread(new SearchThread(directories.remove()));
                    }
                    searchingThreads.add(searchingThread);
                    searchingThread.start();
                } else {
                    while (true) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        searchingThreads = searchingThreads.stream().filter(Thread::isAlive).collect(Collectors.toList());
                    }
                }
                if (directories.isEmpty()) {
                    synchronized (directoriesMonitor) {
                        try {
                            wait(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    class SearchThread implements Runnable {
        private File directory;

        public SearchThread(File directory) {
            this.directory = directory;
        }

        @Override
        public void run() {
            List<String> currentResult = Arrays.stream(directory.listFiles()).map(File::getAbsolutePath).filter(s -> s.endsWith(File.separator + filename)).collect(Collectors.toList());
            if (!currentResult.isEmpty()) {
                synchronized (resultMonitor) {
                    result.add(String.join("\n", currentResult));
                }
            } else {
                List<File> newDirectories = Arrays.stream(directory.listFiles()).filter(File::isDirectory).collect(Collectors.toList());
                synchronized (directoriesMonitor) {
                    directories.addAll(newDirectories);
                    directoriesMonitor.notify();
                }
            }
        }
    }
}
