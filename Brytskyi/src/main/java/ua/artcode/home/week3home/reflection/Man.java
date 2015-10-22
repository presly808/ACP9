package ua.artcode.home.week3home.reflection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * User: huyti
 * Date: 11.10.15
 */
@XmlRootElement(name = "мужчина")
@XmlType(propOrder = {"name", "age", "date"})
public class Man {

//    @Save(name = "age")
    @XmlElement
    private Integer age;
//    @Save(name = "name")
    @XmlElement
    public String name;
//    @Save(name = "date")
    @XmlElement
    private Date date;


    public Man() {
    }

    public Man(int age, String name, Date date) {
        this.age = age;
        this.name = name;
        this.date = date;
    }
    @XmlTransient
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlTransient
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Man{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Man man = (Man) o;

        if (age != null ? !age.equals(man.age) : man.age != null) return false;
        if (date != null ? !date.equals(man.date) : man.date != null) return false;
        if (name != null ? !name.equals(man.name) : man.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

}
