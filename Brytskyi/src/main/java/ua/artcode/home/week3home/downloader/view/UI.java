package ua.artcode.home.week3home.downloader.view;

import ua.artcode.home.week3home.downloader.controller.EXUADownloadController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * User: huyti
 * Date: 15.10.15
 */
public class UI {

    private EXUADownloadController controller;


    private JTextField linkTextField;
    private JTextField formatField;
    private JButton findLinksButton;
    private JButton collectLinksButton;
    private JTextField targetDirectoryField;
    private JButton downloadButton;
    private JProgressBar progressBar1;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel labelsPanel;
    private JPanel findLinksPanel;
    private JPanel CollectLinksPanel;
    private JPanel bottomPanel;
    private JLabel httpLabel;
    private JLabel formatLabel;
    private JLabel bottomLabel;
    private JScrollPane scrollpane;
    private JPanel mid;
    private JTable table;
    private String[][] values = null;

    public UI() {
        super();
        this.controller = new EXUADownloadController();
        findLinksButton.addActionListener(findLinksButtonListener);
        collectLinksButton.addActionListener(collectButtonListener);
        downloadButton.addActionListener(downloadButtonListener);

        controller.setUi(this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Downloader");
        frame.setContentPane(new UI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700, 500);
        frame.setVisible(true);


    }

    private ActionListener findLinksButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                values = fillTable(controller.findAllHrefs(linkTextField.getText()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            String[] bashka = new String[]{"Title", "Link"};
            mid.remove(0);
            table = new JTable(values, bashka);
            JScrollPane scrrp = new JScrollPane(table);
            mid.add(scrrp);
            mid.updateUI();
        }
    };

    private ActionListener collectButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            values = fillTable(controller.collectByFormat(formatField.getText()));
            String[] bashka = new String[]{"Title", "Link"};
            mid.remove(0);
            table = new JTable(values, bashka);
            JScrollPane scrrp = new JScrollPane(table);
            mid.add(scrrp);
            mid.updateUI();
        }
    };

    private ActionListener downloadButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowindex = table.getSelectedRow();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        controller.download(values[rowindex][1], values[rowindex][0], targetDirectoryField.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            };
            progressBar1.setStringPainted(true);
            thread.start();
        }
    };

    public void changeStatusBar(int value) {
        progressBar1.setValue(value);

    }


    private String[][] fillTable(Map<String, String> map) {
        Set<Map.Entry<String, String>> hrefs = map.entrySet();
        String[][] res = new String[hrefs.size()][2];
        int i = 0;
        for (Map.Entry<String, String> e : hrefs) {
            res[i][0] = e.getKey();
            res[i][1] = e.getValue();
            i++;
        }
        return res;
    }

    private void createUIComponents() {
        mid = new JPanel(new GridLayout());
        scrollpane = new JScrollPane();
        mid.add(scrollpane);
    }
}
