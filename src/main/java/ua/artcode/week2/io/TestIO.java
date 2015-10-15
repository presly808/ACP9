package ua.artcode.week2.io;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by serhii on 10/2/15.
 */
public class TestIO {


    public static void main(String[] args) throws IOException {
        System.out.println(IOUtils.readFile("/home/serhii/dev/ACP9_CLONE/ACP9/home/week1.txt"));

        IOUtils.mkdir("temp");

        IOUtils.writeToFile("new line1", "temp/file.txt");


    }
}
