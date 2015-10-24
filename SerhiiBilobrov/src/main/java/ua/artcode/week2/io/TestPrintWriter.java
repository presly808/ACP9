package ua.artcode.week2.io;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by serhii on 22.10.15.
 */
public class TestPrintWriter {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Start app " + System.currentTimeMillis());

        IOUtils.writeViaPrintWriter("test3.txt");
    }
}
