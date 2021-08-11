package com.martins.valet.data.features.entity.disk;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by policante on 7/15/16.
 */
public class VehycleRealm extends RealmObject {

    private String plate;
    private BrandVehycleRealm brand;
    private String color;
    private String observation;

    public VehycleRealm() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public BrandVehycleRealm getBrand() {
        return brand;
    }

    public void setBrand(BrandVehycleRealm brand) {
        this.brand = brand;
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
}
