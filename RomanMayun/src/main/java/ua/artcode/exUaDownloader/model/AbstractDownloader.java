package ua.artcode.exUaDownloader.model;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.artcode.exUaDownloader.view.View;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class AbstractDownloader implements Downloader {
    private String urlRoot = "http://www.ex.ua";
    private String linkAttr = "href";
    private String hrefSelector = "a[href*=/get]";


    protected void downLoadHelper(String directory, Map<String,String> map) throws IOException {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            byte[]arr = IOUtils.getByteArr(new URL(urlRoot + entry.getKey()).openStream());
            System.out.println(View.conditionString = "downloading" + entry.getValue());
            IOUtils.writeBytes(arr, directory + entry.getValue());
        }
    }
    public Map<String ,String> parse(String url,List<String> formats, Map<String ,String > map) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements links = document.select(hrefSelector);
        for (Element link : links) {
            String href = link.attr(linkAttr);
            String songName = link.text();
            formats.stream().filter(musicFormat -> songName.contains(musicFormat)).forEach(musicFormat -> map.put(href, songName));
        }
        return map;
    }
}
