package com.artcode.training.week3.downloader;

import com.artcode.training.utils.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class EXUADownloader {
    /**
     * CSS query for getting all links for downloading
     */
    public static final String ALL_FILES = "a[href^=/load/]:not([href*=?fs_id=])";
    public static final String REFERENCE_ATTRIBUTE_KEY = "href";
    private static final String TITLES = "a[href^=/get/][title]";

    public void download(String prefix, String particularPage, String query) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        Document doc = Jsoup.connect(prefix + particularPage).get();
        Elements links = doc.select(query);
        Elements linkTitles = doc.select(TITLES);
        String title = IOUtils.makeCorrectFolderName(doc.title());
        if (!IOUtils.recreateFolder(title)) {
            System.err.println(title + " can not be created");
            return;
        }
        for (int i = 0; i < links.size(); i++) {
            downloadFile(prefix + links.get(i).attr(REFERENCE_ATTRIBUTE_KEY), title, linkTitles.get(i).text());
        }
    }

    private void downloadFile(String pageForDownloading, String destinationFolder, String fileName) {
        File file = new File(destinationFolder + File.separator + fileName);
        try {
            file.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(IOUtils.getByteArr(new URL(pageForDownloading).openStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        new EXUADownloader().download("http://www.ex.ua", "/77454549?r=82380,80926", ALL_FILES);
    }
}
