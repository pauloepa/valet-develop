package com.martins.valet.data.features.entity.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by policante on 7/26/16.
 */
public class VehycleResponse {

    @Expose
    @SerializedName("id")
    private int identifier;
    @Expose
    @SerializedName("placa")
    private String plate;
    @Expose
    @SerializedName("marca")
    private String brand;
    @Expose
    @SerializedName("cor")
    private String color;
    @Expose
    @SerializedName("obs")
    private String observation;
    @Expose
    @SerializedName("tipo")
    private String type;

    public VehycleResponse() {
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
