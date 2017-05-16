package fr.unice.polytech.jcancela.tobeortohave.model;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class Store {
    String name;
    double latitude;
    double longitude;

    public Store(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
