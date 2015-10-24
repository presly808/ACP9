package ua.artcode.homeWork_1.search;

import java.util.ArrayList;

public class TestBinarySearchContact {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Alex"));
        contacts.add(new Contact("Din"));
        contacts.add(new Contact("Bob"));
        contacts.add(new Contact("Jon"));
        contacts.add(new Contact("Sara"));
        contacts.add(new Contact("Robin"));
        contacts.sort(new java.util.Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });


       // System.out.println(new BinarySearch<>(String::compareTo).search(, "Alex");
    }
}
