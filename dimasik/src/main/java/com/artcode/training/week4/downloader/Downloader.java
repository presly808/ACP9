package com.artcode.training.week4.downloader;

import com.artcode.training.utils.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Downloader {

    private Map<String, Map<String, String>> fileLinks = new HashMap<>();
    private final Object linksMonitor = new Object();
    private Queue<String> newPages = new PriorityQueue<>();
    private final Object newPagesMonitor = new Object();

    class FileDownloadingThread implements Runnable {
        private String pageForDownloading;
        private String destinationFolder;
        private String fileName;

        public FileDownloadingThread(String pageForDownloading, String destinationFolder, String fileName) {
            this.pageForDownloading = pageForDownloading;
            this.destinationFolder = destinationFolder;
            this.fileName = fileName;
        }

        @Override
        public void run() {
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
    }

    class PageAskingThread implements Runnable {

        private PrintStream outs;
        private Scanner sc;
        private ExecutorService service;
        private String prefix;

        public PageAskingThread(PrintStream outs, InputStream is, String prefix) {
            this.outs = outs;
            this.sc = new Scanner(is);
            this.prefix = prefix;
            service = Executors.newFixedThreadPool(25);//todo flexible
        }

        @Override
        public void run() {
            while (true) {
                if (!newPages.isEmpty()) {
                    Stream<String> pagesStream;
                    synchronized (newPagesMonitor) {
                        pagesStream = newPages.stream();
                    }
                    pagesStream.filter(this::askForPageProcessing).forEach(s -> {
                        service.execute(new LinkSearchingThread(prefix, s));
                        synchronized (newPagesMonitor) {
                            newPages.remove(s);
                        }
                    });
                } else {
                    synchronized (newPagesMonitor) {
                        try {
                            newPagesMonitor.wait(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        private boolean askForPageProcessing(String s) {
            String page;
            try {
                page = IOUtils.makeCorrectFolderName(Jsoup.connect(s).get().title());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            outs.printf("Do you want to process page %s?", page);
            return ("y").equalsIgnoreCase(sc.nextLine());
        }
    }

    class FileAskingThread implements Runnable {

        private PrintStream outs;
        private Scanner sc;
        private ExecutorService service;

        public FileAskingThread(PrintStream outs, InputStream is) {
            this.outs = outs;
            this.sc = new Scanner(is);
            service = Executors.newFixedThreadPool(25);//todo flexible
        }

        @Override
        public void run() {
            while (true) {
                if (!fileLinks.isEmpty()) {
                    Stream<Map.Entry<String, Map<String, String>>> linksStream;
                    synchronized (linksMonitor) {
                        linksStream = fileLinks.entrySet().stream();
                    }
                    linksStream.forEach(stringMapEntry -> {
                        stringMapEntry.getValue().entrySet().stream().filter(entry -> askForFileDownloading(entry.getKey())).
                                forEach(entry1 -> service.execute(new FileDownloadingThread(entry1.getValue(), stringMapEntry.getKey(), entry1.getKey())));
                        synchronized (linksMonitor) {
                            fileLinks.remove(stringMapEntry.getKey());
                        }
                    });
                } else {
                    synchronized (linksMonitor) {
                        try {
                            linksMonitor.wait(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        private boolean askForFileDownloading(String s) {
            String page;
            try {
                page = IOUtils.makeCorrectFolderName(Jsoup.connect(s).get().title());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            outs.printf("Do you want to process page %s?", page);
            return ("y").equalsIgnoreCase(sc.nextLine());
        }
    }

    class LinkSearchingThread implements Runnable {
        public static final String ALL_FILES = "a[href^=/load/]:not([href*=?fs_id=])";
        private static final String TITLES = "a[href^=/get/][title]";
        public static final String REFERENCE_ATTRIBUTE_KEY = "href";
        private String prefix;
        private String particularPage;

        public LinkSearchingThread(String prefix, String particularPage) {
            this.prefix = prefix;
            this.particularPage = particularPage;
        }

        @Override
        public void run() {
            Document doc;
            try {
                doc = Jsoup.connect(prefix + particularPage).get();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            Elements links = doc.select(ALL_FILES);
            Elements linkTitles = doc.select(TITLES);
            Map<String, String> currentPageFileLinks = new HashMap<>();
            for (int i = 0; i < links.size(); i++) {
                currentPageFileLinks.put(linkTitles.get(i).text(), prefix + links.get(i).attr(REFERENCE_ATTRIBUTE_KEY));
            }
            synchronized (linksMonitor) {
                fileLinks.put(IOUtils.makeCorrectFolderName(doc.title()), currentPageFileLinks);
                linksMonitor.notify();
            }
            List<String> pages = doc.select("query").stream().map(element -> prefix + element.attr(REFERENCE_ATTRIBUTE_KEY)).collect(Collectors.toList());//todo query
            synchronized (newPagesMonitor) {
                newPages.addAll(pages);
                newPagesMonitor.notify();
            }
        }
    }
}
