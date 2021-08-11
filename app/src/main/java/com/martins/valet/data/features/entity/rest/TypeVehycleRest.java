package com.martins.valet.data.features.entity.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by policante on 7/24/16.
 */
public class TypeVehycleRest {

    @Expose
    @SerializedName("id")
    private String identifier;

    @Expose
    @SerializedName("tipo")
    private String type;

    @Expose
    @SerializedName("icon")
    private String icon;

    @Expose
    @SerializedName("id_app")
    private String idApp;

    public TypeVehycleRest() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }
}
