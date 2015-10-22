package ua.artcode.exUaDownloader;



import ua.artcode.exUaDownloader.model.AbstractDownloader;
import ua.artcode.exUaDownloader.model.DownloadFabric;
import ua.artcode.exUaDownloader.model.Downloads;
import ua.artcode.exUaDownloader.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Controller {
private View view;
private Map<String,String> mapOfCurrentFiles;
private AbstractDownloader downloader;

    public Controller(View view) {
        this.view = view;
        view.setConfirmButtonListener(new SaveListener());
        view.setClearUrlButtonListener(new ClearURLListener());
        view.setFindButtonListener(new FindFilesListener());
        view.setSaveAllButtonListener(new SaveAllListener());
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser directoryChooser = new JFileChooser();
            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            directoryChooser.setCurrentDirectory(new File(Downloads.directory));
            directoryChooser.setDialogTitle("Choose directory");

            // disable the "All files" option.
            directoryChooser.setAcceptAllFileFilterUsed(false);

            if (directoryChooser.showOpenDialog(directoryChooser) == JFileChooser.APPROVE_OPTION) {
                Downloads.directory = directoryChooser.getSelectedFile().getAbsolutePath()+"\\";

            }
            else {
                Downloads.directory = Downloads.DEFAULT_DIRECTORY;
            }
            //показываем обновленную надпись в label
            view.getPathTextField().setText(Downloads.directory);
        }
    }

    private class ClearURLListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getUrlField().setText("");
        }
    }

    private class FindFilesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String boxIndicator = view.getBox().getSelectedItem().toString();
            checkURL();
            downloader = DownloadFabric.getDownloader(boxIndicator);
            try {
//                downloader.download(Downloads.url,Downloads.directory);
                view.getConditionLabel().setText("Searching...");
                StringBuilder builder = new StringBuilder();
                mapOfCurrentFiles = downloader.parse(Downloads.url, Downloads.MUSIC_FORMATS, new TreeMap<>());
                mapOfCurrentFiles.values().stream().forEach(value->{builder.append(" " + value + "\n");});
                view.getTextArea().setText(builder.toString());
                makeFont();
                view.getListSize().setText(view.getListSize().getText() + mapOfCurrentFiles.size());
                view.getConditionLabel().setText("Searching complete");

            } catch (IOException e1) {
                view.showError("Wrong Url");
                e1.printStackTrace();
            }
        }
        private void checkURL(){
            if (view.getUrlField().getText().length()!=0){
                Downloads.url = view.getUrlField().getText();
            }else{
                view.showError("Please check your url");
            }
        }
        private void makeFont(){
            if (mapOfCurrentFiles.size()>25){
                view.getTextArea().setFont(new Font(null,Font.BOLD,8));
            }else view.getTextArea().setFont(new Font(null,Font.BOLD,10));
        }
    }

    private class SaveAllListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("downloading files to " + Downloads.directory);
            makeDownload();
            view.getConditionLabel().setText("Download complete");

        }
        private void makeDownload() {
            try {
                downloader.download(Downloads.directory, mapOfCurrentFiles);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
