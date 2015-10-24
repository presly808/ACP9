package ua.artcode.week3.download;

import ua.artcode.week2.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by serhii on 09.10.15.
 */
public class TestURL {


    public static void main(String[] args) throws IOException {

        byte[] arr = IOUtils.getByteArr(new URL("http://www.ex.ua/load/196481680").openStream());
        IOUtils.writeBytes(arr,"temp/music.mp3");
    }
}
