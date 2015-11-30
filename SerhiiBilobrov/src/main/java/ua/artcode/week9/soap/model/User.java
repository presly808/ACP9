package ua.artcode.week9.soap.model;

/**
 * Created by serhii on 26.11.15.
 */
public class User {


    private int id;
    private String name;
    private int age;
    private double salary;

    public User() {
    }

    public User(int age, int id, String name, double salary) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
