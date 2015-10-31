package com.artcode.training.week2.commands.implementation;

import com.artcode.training.week2.commands.AbstractCommand;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FindFileUsingExecutorServiceCommand extends AbstractCommand {
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
        return "search <file name> <threads limit> - search for file using limited amount of threads\n"
                + "search <file name> - search for file";
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

        public static final int WAIT_FOR_FOLDER_TIMEOUT = 5000;
        public static final int WAIT_FOR_THREAD_FINISH_TIMEOUT = 3000;

        @Override
        public void run() {
            ExecutorService service = Executors.newFixedThreadPool(threadsAmount);
            List<Future<String>> futureList = new ArrayList<>();
            while (!directories.isEmpty()) {
                synchronized (directoriesMonitor) {
                    futureList.add(service.submit(new SearchThread(directories.remove())));
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
            //collect all results
            futureList.parallelStream().forEach(stringFuture -> {
                try {
                    String threadResult = stringFuture.get(WAIT_FOR_THREAD_FINISH_TIMEOUT, TimeUnit.MILLISECONDS);
                    if (!threadResult.isEmpty()) {
                        synchronized (resultMonitor) {
                            result.add(threadResult);
                        }
                    }
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    class SearchThread implements Callable<String> {
        private File directory;

        public SearchThread(File directory) {
            this.directory = directory;
        }

        @Override
        public String call() throws Exception {
            List<File> newDirectories = Arrays.stream(directory.listFiles()).filter(File::isDirectory).collect(Collectors.toList());
            if (!newDirectories.isEmpty()) {
                synchronized (directoriesMonitor) {
                    directories.addAll(newDirectories);
                    directoriesMonitor.notify();
                }
            }
            return String.join(DELIMITER_FOR_RESULT, Arrays.stream(directory.listFiles()).map(File::getAbsolutePath).filter(s -> s.endsWith(File.separator + filename)).collect(Collectors.toList()));
        }
    }
}
