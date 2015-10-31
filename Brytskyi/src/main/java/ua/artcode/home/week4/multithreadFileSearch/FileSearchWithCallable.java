package ua.artcode.home.week4.multithreadFileSearch;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * User: huyti
 * Date: 29.10.2015
 */
public class FileSearchWithCallable {

    private static ExecutorService executorService;

    public static List<File> findAll(String rootPath, String targetName) {
        LinkedList<Callable> tasks = new LinkedList<Callable>();
        List<Future<List<File>>> results = new LinkedList<Future<List<File>>>();
        createTasks(rootPath, targetName, tasks);

//        executorService = Executors.newCachedThreadPool();
        executorService = Executors.newFixedThreadPool(tasks.size());
        for (Callable task : tasks) {
            results.add(executorService.submit(task));
        }
        executorService.shutdown();

        return buildResult(results);
    }

    private static List<File> buildResult(List<Future<List<File>>> results) {
        List<File> result = new LinkedList<File>();
        for (Future<List<File>> listFuture : results) {
            try {
                if (listFuture.get() != null) result.addAll(listFuture.get());
            } catch (InterruptedException e) {
                System.out.println("have some problem");
            } catch (ExecutionException e) {
                System.out.println("Some threads worked without result");;
            }
        }
        return result;
    }

    private static void createTasks(String rootPath, String targetName, List<Callable> tasks) {
        File file = new File(rootPath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            try {
                tasks.add(new FileSearch(file1, targetName));
            } catch (NullPointerException e) {
                System.out.println("problem with file " + file1.getAbsolutePath());
            }
        }
    }


}

class FileSearch implements Callable<List<File>> {
    Queue<File> directories;
    List<File> results;
    File rootFile;
    String targetName;

    public FileSearch(File rootFile, String targetName) {
        this.rootFile = rootFile;
        this.targetName = targetName;
    }

    public List<File> findAll() {
        results = new ArrayList<File>();
        directories = new LinkedList<File>();


        if (rootFile.isDirectory()) {
            lookThroughDirectory(rootFile);
        } else {
            if (isTarget(rootFile, targetName)) results.add(rootFile);
            return results;
        }

        while (true) {
            File newRoot = directories.poll();
            if (newRoot != null) {
                try {
                    lookThroughDirectory(newRoot);
                } catch (NullPointerException e) {
                    System.out.println("problem with" + newRoot.getAbsolutePath());
                }
            } else {
                return results;
            }
        }
    }

    /*дивиться дітей директорії, якщо є варіант, що підходить, записує в результат
    * якщо знаходить діректорію, записує її у чергу спереду*/

    private void lookThroughDirectory(File root) {

        File[] myChildren = root.listFiles();
        for (File f : myChildren) {
            if (f.isDirectory() && f.canRead() && f.canExecute()) {
                directories.add(f);
            }
            if (isTarget(f, targetName)) {
                results.add(f);
            }
        }
    }


    /*chek name of file with targetname*/
    private boolean isTarget(File file, String targetName) {
        if (file.getName().contains(targetName)) return true;
        return false;
    }

    @Override
    public List<File> call() throws Exception {
        return findAll();
    }
}




