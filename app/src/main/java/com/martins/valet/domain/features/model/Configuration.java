package com.martins.valet.domain.features.model;

/**
 * Created by policante on 7/18/16.
 */
public class Configuration {

    private String printerHost;
    private int printerPort;
    private String company;
    private String document;
    private String address;
    private String phone;
    private String obs;

    private String userToken;

    public Configuration() {
        this(null);
    }

    public Configuration(Configuration configuration){
        if (configuration != null){
            this.setPrinterHost(configuration.getPrinterHost());
            this.setPrinterPort(configuration.getPrinterPort());
            this.setCompany(configuration.getCompany());
            this.setDocument(configuration.getDocument());
            this.setAddress(configuration.getAddress());
            this.setPhone(configuration.getPhone());
            this.setObs(configuration.getObs());
            this.userToken = configuration.getUserToken();
        }
    }

    public String getPrinterHost() {
        return printerHost;
    }

    public void setPrinterHost(String printerHost) {
        this.printerHost = printerHost;
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

    public int getPrinterPort() {
        return printerPort;
    }

    public void setPrinterPort(int printerPort) {
        this.printerPort = printerPort;
    }

    public boolean userLoggedIn() {
        return this.userToken != null && !this.userToken.isEmpty();
    }

    public String getUserToken() {
        return this.userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
