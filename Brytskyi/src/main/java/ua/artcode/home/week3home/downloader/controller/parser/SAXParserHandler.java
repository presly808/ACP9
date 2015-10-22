package ua.artcode.home.week3home.downloader.controller.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
/**
 * User: huyti
 * Date: 15.10.15
 */

//looks trough HTML and write in map hrefs and formats
public class SAXParserHandler extends DefaultHandler {
    private Map<String, String> links;

    public SAXParserHandler() {
        links = new HashMap<String, String>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("a")) putLink(attributes);
    }

    // look trough atributes and if link is correct writes result in map (link and title)
    private void putLink(Attributes attributes) {
        String link = null;
        String title = null;
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getQName(i).equals("href")) {
                if (attributes.getValue(i).contains("/get/")) {
                    link = "http://www.ex.ua" + attributes.getValue(i);
                } else return;

            }
            if (attributes.getQName(i).equals("title")) {
                title = attributes.getValue(i);
            } else return;
        }
        if (link != null && title != null) links.put(title, link);
    }

    public Map<String, String> getLinks() {
        return links;
    }
}
