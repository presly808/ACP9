package ua.artcode.home.week4.fileSearchQeue;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * User: huyti
 * Date: 28.10.2015
 */
public class FileSearch {
    Queue<File> directories;
    List<File> results;

    public List<File> findAll(String rootPath, String targetName) {
        results = new ArrayList<File>();
        directories = new LinkedList<File>();

        File root = new File(rootPath);
        if (root.isDirectory()) {
            lookThroughDirectory(root, targetName);
        } else {
            return null;
        }

        while (true) {
            File newRoot = directories.poll();
            if (newRoot != null) {
                try {
                    lookThroughDirectory(newRoot, targetName);
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

    public void lookThroughDirectory(File root, String targetName) {

        File[] myChildren = root.listFiles();
        for (File f : myChildren) {
            if (f.isDirectory() && f.canRead()) {
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

}
