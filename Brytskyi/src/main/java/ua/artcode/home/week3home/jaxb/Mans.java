package ua.artcode.home.week3home.jaxb;

import ua.artcode.home.week3home.reflection.Man;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.*;

/**
 * User: huyti
 * Date: 15.10.15
 */
@XmlRootElement(name = "mujiki")
public class Mans {

    @XmlElement(name = "Mujik")
    @XmlElementWrapper()
    Man[] mens;

    public Mans() {
    }

    public Mans(Man[] mens) {
        this.mens = mens;
    }
}
