package ua.artcode.home.week3home.objtoxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * User: huyti
 * Date: 12.10.15
 */


//using DOM parser write XML from object or make instance of object from XML file
public class XMLObjectParser {

    //obj to parse
    //path where will be XML
    public static boolean objectToXML(Object obj, String path) throws
            ParserConfigurationException,
            TransformerException,
            IllegalAccessException, InstantiationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document docXML = factory.newDocumentBuilder().newDocument();
        Element root;
        if (typeOfObj(obj) == null) {
            root = docXML.createElement(obj.getClass().getName());
            docXML.appendChild(root);
            appendFields(root, obj.getClass().getDeclaredFields(), obj, docXML);
        } else {
            docXML.appendChild(buildByType(docXML, typeOfObj(obj), obj));
        }


        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        DOMSource source = new DOMSource(docXML);
        StreamResult result = new StreamResult(fileController(path));
        transformer.transform(source, result);
        return true;
    }

    private static Element buildByType(Document document, String type, Object obj) throws IllegalAccessException, InstantiationException {
        if (type.equals("Array")) return buildArray(document, obj);
        if (type.equals("AbstractList")) return buildAstractList(document, obj);
        if (type.equals("AbstractSet")) return buildAstractSet(document, obj);
        if (type.equals("Map.Entry")) return buildEntry(document, obj);
        if (type.equals("AbstractMap")) return buildMap(document, obj);

        return null;
    }

    private static Element buildMap(Document document, Object obj) throws InstantiationException, IllegalAccessException {
        Element root = document.createElement("Map");
        root.setAttribute("class", obj.getClass().getName());
        Map map = (Map) obj;
        Set entries = map.entrySet();
        for (Object e : entries) {
            root.appendChild(buildEntry(document, e));
        }
        return root;
    }

    private static Element buildEntry(Document document, Object obj) throws InstantiationException, IllegalAccessException {
        Element root = document.createElement("Entry");
        root.setAttribute("class", obj.getClass().getName());
        Map.Entry entry = (Map.Entry) obj;
        root.setAttribute("key", entry.getKey().toString());
        if (typeOfObj(entry.getValue()) == null) {
            appendFields(root, entry.getValue().getClass().getDeclaredFields(), entry.getValue(), document);
        } else {
            root.appendChild(buildByType(document, typeOfObj(entry.getValue()), entry.getValue()));
        }
        return root;
    }

    private static Element buildAstractSet(Document document, Object obj) throws IllegalAccessException, InstantiationException {
        Element root = document.createElement("Set");
        root.setAttribute("class", obj.getClass().getName());
        Set arr = (Set) obj;
        appendChildren(root, arr.toArray(), document);
        return root;
    }

    private static Element buildAstractList(Document document, Object obj) throws IllegalAccessException, InstantiationException {
        Element root = document.createElement("List");
        root.setAttribute("class", obj.getClass().getName());
        List arr = (List) obj;
        appendChildren(root, arr.toArray(), document);
        return root;
    }

    private static Element buildArray(Document document, Object obj) throws IllegalAccessException, InstantiationException {
        Element root = document.createElement("Array");
        root.setAttribute("class", obj.getClass().getName());
        Object[] arr = (Object[]) obj;
        appendChildren(root, arr, document);
        return root;
    }


    // method converts a simple fields
    private static void appendFields(Element element, Field[] fields, Object obj, Document document) throws IllegalAccessException, InstantiationException {
        for (Field field : fields) {
            field.setAccessible(true);
            if (typeOfObj(field.get(obj)) == null) {
                Element el = document.createElement(field.get(obj).getClass().getName());
                el.appendChild(document.createTextNode(field.get(obj).toString()));
                element.appendChild(el);
            } else {
                element.appendChild(buildByType(document, typeOfObj(field.get(obj)), document));
            }
        }
    }

    // method converts fields like array or collection
    private static void appendChildren(Element root, Object[] arr, Document document) throws IllegalAccessException, InstantiationException {
        int i = 0;
        for (Object ob : arr) {
            if (typeOfObj(ob) == null) {
                Element ele = document.createElement(ob.getClass().getName());
                ele.setAttribute("num", String.valueOf(i));
                i++;
                appendFields(ele, ob.getClass().getDeclaredFields(), ob, document);
                root.appendChild(ele);
            } else {
                root.appendChild(buildByType(document, typeOfObj(ob), ob));
            }
        }
    }

    // define type of object to be converted
    private static String typeOfObj(Object obj) {
        if (obj.getClass().getSuperclass().getSimpleName().equals("AbstractList")) return "AbstractList";
        if (interfacesContainsEntry(obj.getClass().getInterfaces())) return "Map.Entry";
        if (obj.getClass().getSuperclass().getSimpleName().equals("AbstractSet")) return "AbstractSet";
        if (obj.getClass().getSuperclass().getSimpleName().equals("AbstractMap")) return "AbstractMap";
        if (obj.getClass().isArray()) return "Array";
        return null;
    }

    private static boolean interfacesContainsEntry(Class[] interfaces) {
        Class[] ins = interfaces;
        for (Class o : ins) {
            if (o.getName().equals("java.util.Map$Entry")) return true;
        }
        return false;
    }

    // shows does file exists if not create new file
    private static File fileController(String path) {
        File file = new File(path);
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Can not create file" + e.getMessage());
        }
        return file;
    }

    public static Object xmlToObject(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = factory.newDocumentBuilder().parse(fileController(path));
        Element root = document.getDocumentElement();


        return null;
    }

    private static Class defineTypeOfElement(Element element) throws ClassNotFoundException {
        if (element.getTagName().equals("AbstractList")) return Class.forName(element.getAttribute("class"));
        if (element.getTagName().equals("AbstractSet")) return Class.forName(element.getAttribute("class"));
        if (element.getTagName().equals("AbstractMap")) return Class.forName(element.getAttribute("class"));
        if (element.getTagName().equals("Array")) return Class.forName(element.getTagName());

//        if (interfacesContainsEntry(obj.getClass().getInterfaces())) return "Map.Entry";
//        if (obj.getClass().isArray()) return "Array";
        return null;
    }
}

