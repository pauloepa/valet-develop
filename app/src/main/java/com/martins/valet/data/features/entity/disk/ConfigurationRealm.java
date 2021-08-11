package com.martins.valet.data.features.entity.disk;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by policante on 7/15/16.
 */
public class ConfigurationRealm extends RealmObject {

    @PrimaryKey
    private int identifier;
    private String printerHost;
    private int printerPort;
    private String company;
    private String document;
    private String address;
    private String phone;
    private String obs;
    private String userToken;

    public ConfigurationRealm() {
    }

    public String getPrinterHost() {
        return printerHost;
    }

    public void setPrinterHost(String printerHost) {
        this.printerHost = printerHost;
    }

    public int getPrinterPort() {
        return printerPort;
    }

    public void setPrinterPort(int printerPort) {
        this.printerPort = printerPort;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
