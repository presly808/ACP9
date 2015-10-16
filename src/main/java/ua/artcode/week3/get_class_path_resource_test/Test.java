package ua.artcode.week3.get_class_path_resource_test;

import ua.artcode.week2.io.IOUtils;

import java.io.File;
import java.io.InputStream;

/**
 * Created by serhii on 09.10.15.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(IOUtils.readAll(IOUtils.getClassPathResource("/company.xml")));
    }

}
