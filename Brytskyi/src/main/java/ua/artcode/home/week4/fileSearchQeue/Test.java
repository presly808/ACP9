package ua.artcode.home.week4.fileSearchQeue;

import java.io.File;
import java.util.List;

/**
 * User: huyti
 * Date: 29.10.2015
 */
public class Test {
    public static void main(String[] args) {
        FileSearch searcher = new FileSearch();
        long start = System.currentTimeMillis();
        List<File> result = searcher.findAll("D:\\", "ideaIU-14.1.5.exe");
        System.out.println("time = " + (System.currentTimeMillis() - start));
        for (File file : result) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
