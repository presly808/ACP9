package ua.artcode.home.week3home.jaxb;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * User: huyti
 * Date: 14.10.15
 */
public interface Parser {
    Object getObject(File file, Class c) throws JAXBException;
    void parseToXml(Object object, File file) throws JAXBException;
}
