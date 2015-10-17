package ua.artcode.home.week3home.downloader.controller.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * User: huyti
 * Date: 15.10.15
 */
public class TestSAX {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXParserHandler handler = new SAXParserHandler();
        URL url = new URL("http://www.ex.ua/94800005");
        parser.parse(url.openStream(), handler);
        Map<String, String> res = handler.getLinks();
        for (Map.Entry<String, String> para : res.entrySet()) {
            System.out.println(para.toString());
        }

    }
}
