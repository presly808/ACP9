package ua.artcode.week3.day2.xml.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.artcode.week2.io.IOUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by serhii on 09.10.15.
 */
public class DomParserTest {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        Document document = documentBuilder.parse(IOUtils.getClassPathResource("/company.xml"));
        Element root = document.getDocumentElement();
        System.out.printf("element name %s, hasChild %b\n", root.getTagName(), root.hasChildNodes());

        showChildren(root);


    }

    public static String parse(Element el){
        return null;
    }

    public static void showChildren(Element element){
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            if(child.getNodeType() == Node.ELEMENT_NODE){
                Element childElement = (Element) child;
                System.out.printf("element name %s, hasChild %b\n", child.getNodeName(), child.hasChildNodes());
                showChildren(childElement);
            }

        }
    }


}
