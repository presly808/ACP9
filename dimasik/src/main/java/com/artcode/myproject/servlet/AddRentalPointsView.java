package com.artcode.myproject.servlet;

import com.artcode.myproject.model.AppartmentsType;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
public class AddRentalPointsView {
    private MapModel emptyModel;
    private int minCost = 100;
    private int maxCost = 20000;
    private String description;
    private List<AppartmentsType> aptTypes;
    private double lat;
    private double lng;

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }

    public void addRequirement() {
        Marker marker = new Marker(new LatLng(lat, lng), description);
        emptyModel.addOverlay(marker);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Requirenment Added", "types:" + aptTypes + "Min:" + minCost + "Max:" + maxCost + "Lat:" + lat + ", Lng:" + lng));
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public int getMinCost() {
        return minCost;
    }

    public int getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAptTypes(List<AppartmentsType> aptTypes) {
        this.aptTypes = aptTypes;
    }

    public List<AppartmentsType> getAptTypes() {
        return aptTypes;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public AppartmentsType[] getAllTypes() {
        return AppartmentsType.values();
    }
}
