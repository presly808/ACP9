package ua.artcode.home.week3home.downloader.controller;

import java.io.IOException;
import java.util.Map;

/**
 * User: huyti
 * Date: 15.10.15
 */
public class Test {
    public static void main(String[] args) throws IOException {
        EXUADownloadController controller = new EXUADownloadController();
        Map<String, String> map = controller.findAllHrefs("http://www.ex.ua/94800005");
        for (Map.Entry<String, String> en : map.entrySet()) {
            System.out.println(en.toString());
        }
    }
}
