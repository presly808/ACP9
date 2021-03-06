package ua.artcode.eshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends IdAutoGeneratedEntity {

    @Column(unique = true, nullable = false, length = 40)
    private String email;
    @Column
    private String fullname;
    @Column
    private String phone;

    @Transient
    private String secretTempKey;

    // must be hashed before saving
    private String pass;

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "owner")
    private List<Product> products = new ArrayList<>();

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
        setId(id);
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.pass = pass;
    }

    public String getSecretTempKey() {
        return secretTempKey;
    }

    public void setSecretTempKey(String secretTempKey) {
        this.secretTempKey = secretTempKey;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
                getId(),email,fullname,phone);
    }


}



