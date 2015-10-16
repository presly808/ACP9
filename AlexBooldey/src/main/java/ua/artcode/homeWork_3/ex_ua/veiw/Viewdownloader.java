package ua.artcode.homeWork_3.ex_ua.veiw;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class ViewDownloader extends JFrame {
    private static final String TITLE = "Downloader for ex.ua ";

    private JTextField pathFolderField;
    private JButton pathButton;
    private JTextField URLField;
    public JProgressBar progressBar1;
    private JButton downloadButton;
    private JPanel viewDownload;

    public ViewDownloader() {
        super(TITLE);
        setContentPane(viewDownload);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(500, 175);
        setResizable(false);

        setVisible(true);
    }

    public String getURLField() {
        return URLField.getText();
    }

    public String getChoiceOfWayDownload() {
        String path = "";
        JFileChooser dialog = new JFileChooser();
        dialog.setCurrentDirectory(new File("//"));
        dialog.setDialogTitle("Download to...");
        dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dialog.setAcceptAllFileFilterUsed(false);

        if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = String.valueOf(dialog.getCurrentDirectory());
        }
        return path;
    }

    public void setActionListenerPathButton(ActionListener listener) {
        pathButton.addActionListener(listener);
    }

    public void setActionListenerDownloadButton(ActionListener listener) {
        downloadButton.addActionListener(listener);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void showPathToFild(String path) {
        pathFolderField.setText(path);
    }


}