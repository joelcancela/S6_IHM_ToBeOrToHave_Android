package fr.unice.polytech.jcancela.tobeortohave.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class Store implements Parcelable {
    String name;
    double latitude;
    double longitude;

    public Store(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Store(Parcel in) {
        double[] data = new double[2];
        in.readDoubleArray(data);
        this.latitude = data[0];
        this.longitude = data[1];
        this.name = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDoubleArray(new double[] {this.latitude,this.longitude});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        public Store[] newArray(int size) {
            return new Store[size];
        }
    };
}
