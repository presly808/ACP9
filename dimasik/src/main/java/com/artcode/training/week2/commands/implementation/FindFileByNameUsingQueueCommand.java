package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FindFileByNameUsingQueueCommand extends AbstractCommand {
    public static final int DEFAULT_THREADS_AMOUNT = 25;
    public static final String DELIMITER_FOR_RESULT = "\n";
    public int threadsAmount;
    private String filename;
    private List<String> result;
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
        result = new ArrayList<>();
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
        newCurrentFile = file;
        return String.join(DELIMITER_FOR_RESULT, result);
    }

    class SearchThreadsProducer implements Runnable {

        public static final int PING_THREADS_DELAY = 1000;
        public static final int WAIT_FOR_FOLDER_TIMEOUT = 5000;
        public static final int WAIT_FOR_THREAD_FINISH_TIMEOUT = 3000;

        @Override
        public void run() {
            List<Thread> searchingThreads = new ArrayList<>();
            while (!directories.isEmpty()) {
                if (searchingThreads.size() < threadsAmount && !directories.isEmpty()) {
                    Thread searchingThread;
                    synchronized (directoriesMonitor) {
                        searchingThread = new Thread(new SearchThread(directories.remove()));
                    }
                    searchingThreads.add(searchingThread);
                    searchingThread.start();
                } else {
                    //wait until some thread will finish his job
                    while (searchingThreads.size() == threadsAmount) {
                        searchingThreads = searchingThreads.stream().filter(Thread::isAlive).collect(Collectors.toList());
                        try {
                            Thread.sleep(PING_THREADS_DELAY);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (directories.isEmpty()) { // wait for new folder
                    synchronized (directoriesMonitor) {
                        try {
                            directoriesMonitor.wait(WAIT_FOR_FOLDER_TIMEOUT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            //wait for all threads
            searchingThreads.stream().forEach((thread) -> {
                try {
                    thread.join(WAIT_FOR_THREAD_FINISH_TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
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
                String joinedResult = String.join(DELIMITER_FOR_RESULT, currentResult);
                synchronized (resultMonitor) {
                    result.add(joinedResult);
                }
            }
            List<File> newDirectories = Arrays.stream(directory.listFiles()).filter(File::isDirectory).collect(Collectors.toList());
            if (!newDirectories.isEmpty()) {
                synchronized (directoriesMonitor) {
                    directories.addAll(newDirectories);
                    directoriesMonitor.notify();
                }
            }
        }
    }
}
