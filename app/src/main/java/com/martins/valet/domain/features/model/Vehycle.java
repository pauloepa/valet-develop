package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;

/**
 * Created by policante on 7/15/16.
 */
public class Vehycle implements Parcelable {

    private String plate;
    private String type;
    private String brand;
    private String color;
    private String observation;

    public Vehycle() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.plate);
        dest.writeString(this.type);
        dest.writeString(this.brand);
        dest.writeString(this.color);
        dest.writeString(this.observation);
    }

    protected Vehycle(Parcel in) {
        this.plate = in.readString();
        this.type = in.readString();
        this.brand = in.readString();
        this.color = in.readString();
        this.observation = in.readString();
    }

    public static final Parcelable.Creator<Vehycle> CREATOR = new Parcelable.Creator<Vehycle>() {
        @Override
        public Vehycle createFromParcel(Parcel source) {
            return new Vehycle(source);
        }

        @Override
        public Vehycle[] newArray(int size) {
            return new Vehycle[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
