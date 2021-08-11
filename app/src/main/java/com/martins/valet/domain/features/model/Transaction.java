package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by policante on 7/11/16.
 */
public class Transaction implements Parcelable {

    private String identifier;
    private Vehycle vehycle;
    private Client client;
    private Date transactionIn;
    private Date transactionOut;
    private Date syncronizedIn;
    private Date syncronizedOut;
    private String agreement;
    private float timeTotal;
    private float valueTotal;
    private float valueParcial;
    private boolean hasProcessed;
    private boolean mensal;

    public Transaction() {
    }

    public Vehycle getVehycle() {
        return vehycle;
    }

    public void setVehycle(Vehycle vehycle) {
        this.vehycle = vehycle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTransactionIn() {
        return transactionIn;
    }

    public void setTransactionIn(Date transactionIn) {
        this.transactionIn = transactionIn;
    }

    public Date getTransactionOut() {
        return transactionOut;
    }

    public void setTransactionOut(Date transactionOut) {
        this.transactionOut = transactionOut;
    }

    public float getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(float timeTotal) {
        this.timeTotal = timeTotal;
    }

    public float getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(float valueTotal) {
        this.valueTotal = valueTotal;
    }

    public Date getSyncronizedIn() {
        return syncronizedIn;
    }

    public void setSyncronizedIn(Date syncronizedIn) {
        this.syncronizedIn = syncronizedIn;
    }

    public Date getSyncronizedOut() {
        return syncronizedOut;
    }

    public void setSyncronizedOut(Date syncronizedOut) {
        this.syncronizedOut = syncronizedOut;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getIdentifierWithSpace(){
        return identifier.replaceAll("", " ");
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public float getValueParcial() {
        return valueParcial;
    }

    public void setValueParcial(float valueParcial) {
        this.valueParcial = valueParcial;
    }

    public boolean isMensal() {
        return mensal;
    }

    public void setMensal(boolean mensal) {
        this.mensal = mensal;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeParcelable(this.vehycle, flags);
        dest.writeParcelable(this.client, flags);
        dest.writeLong(this.transactionIn != null ? this.transactionIn.getTime() : -1);
        dest.writeLong(this.transactionOut != null ? this.transactionOut.getTime() : -1);
        dest.writeLong(this.syncronizedIn != null ? this.syncronizedIn.getTime() : -1);
        dest.writeLong(this.syncronizedOut != null ? this.syncronizedOut.getTime() : -1);
        dest.writeString(this.agreement);
        dest.writeFloat(this.timeTotal);
        dest.writeFloat(this.valueTotal);
        dest.writeFloat(this.valueParcial);
        dest.writeByte(this.hasProcessed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mensal ? (byte) 1 : (byte) 0);
    }

    protected Transaction(Parcel in) {
        this.identifier = in.readString();
        this.vehycle = in.readParcelable(Vehycle.class.getClassLoader());
        this.client = in.readParcelable(Client.class.getClassLoader());
        long tmpTransactionIn = in.readLong();
        this.transactionIn = tmpTransactionIn == -1 ? null : new Date(tmpTransactionIn);
        long tmpTransactionOut = in.readLong();
        this.transactionOut = tmpTransactionOut == -1 ? null : new Date(tmpTransactionOut);
        long tmpSyncronizedIn = in.readLong();
        this.syncronizedIn = tmpSyncronizedIn == -1 ? null : new Date(tmpSyncronizedIn);
        long tmpSyncronizedOut = in.readLong();
        this.syncronizedOut = tmpSyncronizedOut == -1 ? null : new Date(tmpSyncronizedOut);
        this.agreement = in.readString();
        this.timeTotal = in.readFloat();
        this.valueTotal = in.readFloat();
        this.valueParcial = in.readFloat();
        this.hasProcessed = in.readByte() != 0;
        this.mensal = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
