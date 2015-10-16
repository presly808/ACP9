package ua.artcode.homeWork_3.ex_ua.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class PageParser {

    private final String FILES = "a[href^=/load/]:not([href*=?fs_id=])";
    private final String SITE_ADDRESS = "http://ex.ua";
    private final String TITLES = "a[href^=/get/][title]";
    private final String ATTRIBUTE_KEY = "href";

    public HashMap<String, String> linkAndParse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elementURL = doc.select(FILES);
        Elements elementsTitle = doc.select(TITLES);
        HashMap<String, String> linksAndNames = new HashMap<>();

        for (int i = 0; i < elementURL.size(); i++) {
            linksAndNames.put(SITE_ADDRESS + elementURL.get(i).attr(ATTRIBUTE_KEY), elementsTitle.get(i).text());
        }

        return linksAndNames;
    }
}
