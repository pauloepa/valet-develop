package com.martins.valet.data.features.entity.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by policante on 7/29/16.
 */
public class TransactionOutRest {

    @Expose
    @SerializedName("id")
    private String ticket;
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
    @Expose
    @SerializedName("datasaida")
    private Date finish;
    @Expose
    @SerializedName("convenio")
    private String agreement;
    @Expose
    @SerializedName("valor")
    private float valueTotal;
    @Expose
    @SerializedName("a_faturar")
    private float valueParcial;
    @Expose
    @SerializedName("tempo")
    private float time;
    @Expose
    @SerializedName("mensal")
    private int mensal;

    private boolean hasProcessed;

    public TransactionOutRest() {
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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

    public float getValueParcial() {
        return valueParcial;
    }

    public void setValueParcial(float valueParcial) {
        this.valueParcial = valueParcial;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public boolean isHasProcessed() {
        return hasProcessed;
    }

    public void setHasProcessed(boolean hasProcessed) {
        this.hasProcessed = hasProcessed;
    }

    public float getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(float valueTotal) {
        this.valueTotal = valueTotal;
    }

    public int getMensal() {
        return mensal;
    }

    public void setMensal(int mensal) {
        this.mensal = mensal;
    }
}
