package ua.artcode.home.week3home.downloader.model;

import ua.artcode.home.week3home.downloader.view.UI;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: huyti
 * Date: 15.10.15
 */
public class Downloader {
    public double persents = 0;

    private UI ui;

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void download(URL sourse, File target) throws IOException {
        URLConnection connection = sourse.openConnection();
        int allBytes = connection.getContentLength();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = sourse.openStream();
            out = new FileOutputStream(target);
            // Copy the bits from instream to outstream
            System.out.println(allBytes);
            byte[] buf = new byte[2048];
            int len;
            int i = 1;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
                i++;
                setPersents(allBytes, i);
                ui.changeStatusBar(getPersents());
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setPersents(int allBytes, int amountOfWritten) {
        persents = ((2048 * amountOfWritten) / allBytes) * 100;
    }

    private int getPersents() {
        return (int) persents;
    }
}
