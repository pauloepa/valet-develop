package com.martins.valet.data.features.entity.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by policante on 7/24/16.
 */
public class BrandVehycleRest {

    @Expose
    @SerializedName("id")
    private int identifier;
    @Expose
    @SerializedName("marca")
    private String name;
    @Expose
    @SerializedName("icon")
    private String icon;
    @Expose
    @SerializedName("tipo")
    private String type;
    @Expose
    @SerializedName("indice")
    private String index;
    @Expose
    @SerializedName("id_app")
    private String idApp;

    public BrandVehycleRest() {
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
