package ua.artcode.home.week3home.objtoxml;

import ua.artcode.home.week3home.reflection.Man;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.*;

/**
 * User: huyti
 * Date: 13.10.15
 */
public class Test {


    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        Man[] men = new Man[]{new Man(24, "Vasia", new Date()),
                new Man(28, "Petia", new Date())};

//        success
        XMLObjectParser.objectToXML(new Man[]{new Man(24, "Vasia", new Date()),
                new Man(28, "Petia", new Date())}, "src/ua.artcode.home.temp/men.xml");
//
        //success
// XMLObjectParser.objectToXML(new Man(24, "Vasia", new Date()), "src/ua.artcode.home.temp/men.xml");

//        success
        ArrayList<Man> mens = new ArrayList<Man>();
        mens.add(new Man(24, "Vasia", new Date()));
        mens.add(new Man(28, "Petia", new Date()));
        XMLObjectParser.objectToXML(mens, "src/ua.artcode.home.temp/men.xml");


        //success
//        Set<Man> set = new HashSet<Man>();
//        set.add(new Man(24, "Vasia", new Date()));
//        set.add(new Man(28, "Petia", new Date()));
//        XMLObjectParser.objectToXML(set, "src/ua.artcode.home.temp/men.xml");
//

        // success
//        ManEntry ent = new ManEntry(1, new Man(24, "Vasia", new Date()));
//        XMLObjectParser.objectToXML(ent, "src/ua.artcode.home.temp/men.xml");


//        Map map = new HashMap<Integer , Man>();
//        map.put(1, new Man(24, "Vasia", new Date()));
//        map.put(2, new Man(29, "Petia", new Date()));
//        Set set = new HashSet<>();
//        set.add(map);
//        XMLObjectParser.objectToXML(set, "src/ua.artcode.home.temp/men.xml");

        Man[] arr = new Man[]{new Man(24, "Vasia", new Date()),
                new Man(28, "Petia", new Date())};
        Man man = new Man(24, "Vasia", new Date());

    }


}
