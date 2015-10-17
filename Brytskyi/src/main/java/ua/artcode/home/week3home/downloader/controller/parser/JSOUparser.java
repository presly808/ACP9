package ua.artcode.home.week3home.downloader.controller.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * User: huyti
 * Date: 15.10.15
 */
public class JSOUparser {
    private String url;
    private Map<String, String> links;

    public JSOUparser() {
        links = new HashMap<String, String>();
    }

    public void putLinks() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByAttributeValueContaining("href", "get");
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).hasAttr("title")) {
                Attributes attributes = elements.get(i).attributes();
                String title = null;
                String href = null;
                for (Attribute atr : attributes) {
                    if (atr.getKey().equals("title")) title = atr.getValue();
                    if (atr.getKey().equals("href")) href = "http://www.ex.ua"+atr.getValue();
                }
                links.put(title, href);
            }
        }
    }


    public Map<String, String> getLinks() {
        return links;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
