package fr.unice.polytech.jcancela.tobeortohave.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class Product implements Parcelable {
    private String name;
    private float price;
    private String thumbnailName;
    private String description;

    public Product(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        this.name = data[0];
        this.description = data[1];
        this.thumbnailName = data[2];
        this.price = in.readFloat();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name,
                this.description,
                this.thumbnailName});
        dest.writeFloat(this.price);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
