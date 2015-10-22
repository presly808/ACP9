package ua.artcode.homeWork_3.ex_ua.control;

import ua.artcode.homeWork_3.ex_ua.model.FileDownloader;
import ua.artcode.homeWork_3.ex_ua.model.PageParser;
import ua.artcode.homeWork_3.ex_ua.veiw.ViewDownloader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControlDownload {
    private String path;
    private String exPage;

    private FileDownloader fileDownloader;
    private PageParser pageParser;

    private ViewDownloader viewDownloader;

    public ControlDownload(ViewDownloader viewDownloader, PageParser pageReader, FileDownloader fileDownloader) {
        this.viewDownloader = viewDownloader;
        this.pageParser = pageReader;
        this.fileDownloader = fileDownloader;
        this.viewDownloader.setActionListenerPathButton(new PathListener());
        this.viewDownloader.setActionListenerDownloadButton(new DownListener());

    }

    public void readerAndParsePage() throws IOException {
        HashMap<String, String> linksAndNames = pageParser.linkAndParse(exPage);
        downloadFiles(linksAndNames);
    }

    private void downloadFiles(HashMap<String, String> linksAndNames) throws IOException {
        for (Map.Entry<String, String> link : linksAndNames.entrySet()) {
            fileDownloader.download(link.getKey(), path, link.getValue());
        }
    }

    private class PathListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                path = viewDownloader.getChoiceOfWayDownload();
                viewDownloader.showPathToFild(path);
            } catch (Exception e1) {
                viewDownloader.showError("Incorrect path");
            }
        }
    }

    private class DownListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                exPage = viewDownloader.getURLField();
                if (exPage != null) {
                    readerAndParsePage();
                } else {
                    viewDownloader.showError("Enter the path!");
                }
            } catch (Exception e1) {
                viewDownloader.showError("Incorrect link");
            }
        }
    }
}