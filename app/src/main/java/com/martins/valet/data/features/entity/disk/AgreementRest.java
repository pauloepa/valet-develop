package com.martins.valet.data.features.entity.disk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by policante on 8/4/16.
 */
public class AgreementRest {

    @Expose
    @SerializedName("id")
    private String identifier;
    @Expose
    @SerializedName("convenio")
    private String name;
    @Expose
    @SerializedName("tipo")
    private String type;
    @Expose
    @SerializedName("valor")
    private float value;

    public AgreementRest() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
