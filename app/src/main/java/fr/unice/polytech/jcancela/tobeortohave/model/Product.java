package fr.unice.polytech.jcancela.tobeortohave.model;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class Product {
    private String name;
    private float price;
    private String thumbnailName;
    private String description;

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }


    public Product(String name, float price, String description, String thumbnailName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.thumbnailName = thumbnailName;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }
}
