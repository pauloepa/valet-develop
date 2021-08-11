package com.martins.valet.data.features.entity.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by policante on 7/14/16.
 */
public class TransactionInRest {

    @Expose
    @SerializedName("placa")
    private String plate;
    @Expose
    @SerializedName("marca")
    private String brand;
    @Expose
    @SerializedName("tipo")
    private String type;
    @Expose
    @SerializedName("cor")
    private String color;
    @Expose
    @SerializedName("dataentrada")
    private Date created;

    public TransactionInRest() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
