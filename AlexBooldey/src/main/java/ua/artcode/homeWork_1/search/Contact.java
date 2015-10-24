package ua.artcode.homeWork_1.search;

public class Contact implements Comparable<Contact> {
    String name;

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return !(name != null ? !name.equals(contact.name) : contact.name != null);

    }

    @Override
    public int compareTo(Contact o) {
        return name.compareTo(o.getName());
    }
}
