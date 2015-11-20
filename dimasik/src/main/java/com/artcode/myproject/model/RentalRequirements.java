package com.artcode.myproject.model;

import com.artcode.training.week3.xml.model.ToPrint;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class RentalRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @ElementCollection(targetClass = AppartmentsType.class)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "apt_types", joinColumns = @JoinColumn(name = "requirement"))
    private List<AppartmentsType> suitableTypes;

    @Column(nullable = false)
    @ToPrint
    private int cost;

    @Column(nullable = false)
    @ToPrint
    private String descriprion;

    public RentalRequirements() {
    }

    public void setSuitableTypes(List<AppartmentsType> suitableTypes) {
        this.suitableTypes = suitableTypes;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public RentalRequirements(int cost, String descriprion, List<AppartmentsType> appartmentsTypes) {
        this.cost = cost;
        this.descriprion = descriprion;
        this.suitableTypes = appartmentsTypes;
    }


    public List<AppartmentsType> getSuitableTypes() {
        return suitableTypes;
    }

    public int getCost() {
        return cost;
    }

    public String getDescriprion() {
        return descriprion;
    }

    @Override
    public String toString() {
        return "RentalRequirements{" +
                "suitableTypes=" + suitableTypes +
                ", cost=" + cost +
                ", descriprion='" + descriprion + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
