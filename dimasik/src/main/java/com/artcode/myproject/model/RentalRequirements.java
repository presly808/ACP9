package com.artcode.myproject.model;

import com.artcode.training.week3.xml.model.ToPrint;

import java.util.List;

/**
 * Created by dmitriyg on 9/25/2015.
 */
public class RentalRequirements {
    private List<AppartmentsType> suitableTypes;
    @ToPrint
    private int cost;
    @ToPrint
    private String descriprion;

    public RentalRequirements(int cost, String descriprion) {
        this.cost = cost;
        this.descriprion = descriprion;
    }
}
