package com.artcode.training.week3.xml.controller;

import com.artcode.training.week3.xml.model.ObjectReader;
import com.artcode.training.week3.xml.model.ObjectWriter;

import java.io.IOException;

public class XMLController {
    public static Object executeCommand(boolean write, String filePath, Object o) throws IOException {
        if (write) {
            ObjectWriter.writeToXML(o, filePath);
            return true;
        } else {
            return ObjectReader.read(filePath);
        }
    }
}
