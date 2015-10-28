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
        List<File> result = searcher.findAll("D:\\", ".exe");
        for (File file : result) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
