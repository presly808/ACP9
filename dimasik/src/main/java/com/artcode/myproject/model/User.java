package com.artcode.myproject.model;

import com.artcode.training.week3.xml.model.ToPrint;

public class User {
    private int id;
    @ToPrint
    private String username;
    private String password;
    @ToPrint
    private RentalRequirements rentalRequirements;
    @ToPrint
    private int age;

    public User(String username, String password, RentalRequirements rentalRequirements, int age) {
        this.username = username;
        this.password = password;
        this.rentalRequirements = rentalRequirements;
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRentalRequirements(RentalRequirements rentalRequirements) {
        this.rentalRequirements = rentalRequirements;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RentalRequirements getRentalRequirements() {
        return rentalRequirements;
    }

    public int getAge() {
        return age;
    }
}
