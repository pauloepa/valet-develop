package com.martins.valet.domain.features.model;

/**
 * Created by policante on 8/4/16.
 */
public class Agreement {

    private String identifier;
    private String name;
    private String type;
    private float value;

    public Agreement() {
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
