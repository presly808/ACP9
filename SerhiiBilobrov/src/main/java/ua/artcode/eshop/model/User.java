package ua.artcode.eshop.model;

/**
 * Created by serhii on 24.09.15.
 */
public class User {

    private int id;
    private String email;
    private String fullname;
    private String phone;
    private String pass;

    public User() {
    }

    public User(String email, String fullname, String phone, String pass) {
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.pass = pass;
    }

    public User(int id, String email, String fullname,
                String phone, String pass) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    // rules of overriding
    public String toString(){
        // Formatter
        return String.format("Id %d, email %s, fullname %s, phone %s",
                id,email,fullname,phone);
    }
}



