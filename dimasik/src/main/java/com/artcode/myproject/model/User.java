package com.artcode.myproject.model;

import com.artcode.training.week3.xml.model.ToPrint;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @ToPrint
    private String username;

    @Column
    private String password;

    @ToPrint
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "requirements_id")
    private RentalRequirements rentalRequirements;

    @Column
    @ToPrint
    private int age;

    public User(String username, String password, RentalRequirements rentalRequirements, int age) {
        this.username = username;
        this.password = password;
        this.rentalRequirements = rentalRequirements;
        this.age = age;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RentalRequirements getRentalRequirements() {
        return rentalRequirements;
    }

    public void setRentalRequirements(RentalRequirements rentalRequirements) {
        this.rentalRequirements = rentalRequirements;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", rentalRequirements=" + rentalRequirements +
                ", age=" + age +
                '}';
    }
}
