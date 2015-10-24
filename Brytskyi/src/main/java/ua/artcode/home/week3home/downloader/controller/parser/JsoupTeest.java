package ua.artcode.home.week3home.downloader.controller.parser;

import java.io.IOException;
import java.util.Map;

/**
 * User: huyti
 * Date: 15.10.15
 */
public class JsoupTeest {


    public static void main(String[] args) throws IOException {
        JSOUparser parser = new JSOUparser();
        parser.setUrl("http://www.ex.ua/94800005");
        parser.putLinks();
        Map<String, String> res = parser.getLinks();
        for (Map.Entry<String, String> para : res.entrySet()) {
            System.out.println(para.toString());
        }
    }
}
