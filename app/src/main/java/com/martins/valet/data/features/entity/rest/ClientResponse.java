package com.martins.valet.data.features.entity.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by policante on 7/26/16.
 */
public class ClientResponse {

    @Expose
    @SerializedName("id_cliente")
    private int identifier;
    @Expose
    @SerializedName("cpfcnpj")
    private String document;
    @Expose
    @SerializedName("nome")
    private String name;
    @Expose
    @SerializedName("obs")
    private String observation;

    public ClientResponse() {
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
