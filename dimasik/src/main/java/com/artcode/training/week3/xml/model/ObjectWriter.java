package com.artcode.training.week3.xml.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectWriter {

    public static final String CLASS_TAG_NAME = "class";
    public static final String TYPE_TAG_NAME = "type";
    public static final String TAIL_FOR_PARENT_NODE = "s";
    public static final String INDENT_PROPERTY_VALUE = "yes";
    public static final String ARRAY = "array";

    public static void writeToXML(Object o, String filePath) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = makeNode(o, document, null);
            document.appendChild(root);
            File file = new File(filePath);
            recreateFile(file);
            //write to file using transformer
            try (FileWriter fileWriter = new FileWriter(file)) {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, INDENT_PROPERTY_VALUE);
                transformer.transform(new DOMSource(document), new StreamResult(fileWriter));
            }
        } catch (ParserConfigurationException | TransformerException | IllegalAccessException e) {
            System.out.println("Some inner problem, please try again\n" + e.getMessage());
        }
    }

    private static void recreateFile(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    private static Element getRoot(Object o, Document document, boolean isForMultiple) {
        return document.createElement(o.getClass().getSimpleName() + (isForMultiple ? TAIL_FOR_PARENT_NODE : ""));
    }

    private static Element makeNode(Object o, Document document, Element root) throws IllegalAccessException {
        Element result = null;
        //adding nodes if incoming object is array
        if (o.getClass().isArray()) {
            //getting type of array from first element
            result = getRoot(Array.get(o, 0), document, true);
            result.setAttribute(CLASS_TAG_NAME, ARRAY);
            for (int i = 0; i < Array.getLength(o); i++) {
                result = makeNode(Array.get(o, i), document, result);
            }

        } else if (o instanceof Iterable) {//adding nodes if incoming object is collection
            for (Object o1 : ((Iterable) o)) {
                if (result == null) {
                    result = getRoot(o1, document, true);
                    result.setAttribute(CLASS_TAG_NAME, o.getClass().getName());
                }
                result = makeNode(o1, document, result);
            }
        } else {
            result = getRoot(o, document, false);
            result.setAttribute(CLASS_TAG_NAME, o.getClass().getName());
            List<Field> fields = getAnnotatedFields(o);
            if (fields.isEmpty()) {
                result.setTextContent(o.toString());
            } else {
                for (Field field : fields) {
                    Element node = document.createElement(field.getName());
                    node.setAttribute(TYPE_TAG_NAME, field.getType().getSimpleName());
                    field.setAccessible(true);
                    //checking if field is complex object
                    List<Field> localFields = getAnnotatedFields(field.get(o));
                    if (localFields.isEmpty()) {
                        node.setTextContent(String.valueOf(field.get(o)));
                    } else {
                        node = makeNode(field.get(o), document, node);
                    }
                    result.appendChild(node);
                }
            }
        }
        if (root != null) {
            root.appendChild(result);
        } else {
            root = result;
        }
        return root;
    }

    private static List<Field> getAnnotatedFields(Object o) throws IllegalAccessException {
        return Arrays.stream(o.getClass().getDeclaredFields()).filter(field1 -> Arrays.stream(field1.getAnnotations()).anyMatch(annotation -> annotation instanceof ToPrint)).collect(Collectors.toList());
    }
}
