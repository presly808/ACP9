package ua.artcode.home.week3home.downloader.controller;

import ua.artcode.home.week3home.downloader.controller.parser.JSOUparser;
import ua.artcode.home.week3home.downloader.model.Downloader;
import ua.artcode.home.week3home.downloader.view.UI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * User: huyti
 * Date: 15.10.15
 */
public class EXUADownloadController {

    private JSOUparser parser;
    private Downloader downloader;
    Map<String, String> hrefs;


    public EXUADownloadController() {
        this.parser = new JSOUparser();
        this.downloader = new Downloader();
    }


    public void setUi(UI ui) {
        downloader.setUi(ui);
    }

    public Downloader getDownloader() {
        return downloader;
    }

    //find all hrefs on page which give a posibility to download files
    public Map<String, String> findAllHrefs(String url) throws IOException {
        parser.setUrl(url);
        parser.putLinks();
        this.hrefs = parser.getLinks();
        return this.hrefs;
    }


    // collect hrefs by format and return map with links of file with such format
    public Map<String, String> collectByFormat(String format) {
        Map<String, String> result = new HashMap<String, String>();
        for (String title : hrefs.keySet()) {
            if (title.endsWith(format)) result.put(title, hrefs.get(title));
        }
        return result;
    }

    public void download(String url, String title, String destinationDirectory) throws IOException {
        File file = new File(destinationDirectory + "/" + title);
        if (!file.exists()) file.createNewFile();
        downloader.download(new URL(url), file);
    }

}
