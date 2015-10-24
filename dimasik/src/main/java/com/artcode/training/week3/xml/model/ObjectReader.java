package com.artcode.training.week3.xml.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ObjectReader {
    public static Object read(String filePath) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
            System.out.println("some inner problems, try again");
            return null;
        }
        Document document;
        try {
            document = documentBuilder.parse(new File(filePath));
        } catch (SAXException e) {
            System.out.println("parsing problems " + e.getMessage());
            return null;
        }
        Element element = document.getDocumentElement();
        try {
            return parseChild(element.getChildNodes(), element.getAttribute(ObjectWriter.CLASS_TAG_NAME));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object parseChild(NodeList childNodes, String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object object = Class.forName(className).newInstance();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;
            String nodeName = node.getNodeName();

        }
        return null;
    }
}
