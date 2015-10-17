package ua.artcode.home.week3home.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * User: huyti
 * Date: 14.10.15
 */
public class JAXBParser implements Parser {
    @Override
    public Object getObject(File file, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);

        return object;
    }

    @Override
    public void parseToXml(Object object, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object .getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(object ,file);
    }

}
