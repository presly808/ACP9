package ua.artcode.homeWork_3.ex_ua.model;

import java.io.*;
import java.net.URL;

public class FileDownloader {

    private InputStream in;
    private FileOutputStream fos;
    private ByteArrayOutputStream out;

    public void download(String url, String path, String name) throws IOException {

        in = new BufferedInputStream(new URL(url).openStream());
        out = new ByteArrayOutputStream();

        byte[] response = read(in);
        try {
            fos = new FileOutputStream(new File(path + "\\" + name));
            fos.write(response);
        } finally {
            if (fos != null)
                fos.close();
        }
    }

    private byte[] read(InputStream in) throws IOException {

        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        int b = 0;
        System.out.println("Downloading...");
        try {
            while((b = in.read()) != -1){
                byos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byos.toByteArray();
    }
}