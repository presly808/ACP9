package ua.artcode.home.week3home.jaxb;

import ua.artcode.home.week3home.reflection.Man;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * User: huyti
 * Date: 14.10.15
 */
public class Test {

    public static void main(String[] args) throws JAXBException {

        //create object
        Man[] arr = new Man[]{new Man(24, "Vasia", new Date()),
                new Man(28, "Petia", new Date())};
        Mans mens = new Mans(arr);
        Man man = new Man(24, "Vasia", new Date());


        //create file
        File file = new File("src/ua.artcode.home.temp/men2.xml");
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Can not create file" + e.getMessage());
        }

        //try to parse
        Parser jaxb = new JAXBParser();
        jaxb.parseToXml(mens, file);
    }
}
