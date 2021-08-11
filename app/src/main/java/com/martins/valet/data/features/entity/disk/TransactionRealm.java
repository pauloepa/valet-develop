package com.martins.valet.data.features.entity.disk;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by policante on 7/13/16.
 */
public class TransactionRealm extends RealmObject {

    @PrimaryKey
    private String identifier;
    private ClientRealm client;
    private VehycleRealm vehycle;
    private TablePriceRealm tablePrice;
    private Date transactionIn;
    private Date transactionOut;
    private float timeTotal;
    private float valueTotal;
    private Date syncronizedIn;
    private Date syncronizedOut;

    public TransactionRealm() {
    }

    public ClientRealm getClient() {
        return client;
    }

    public void setClient(ClientRealm client) {
        this.client = client;
    }

    public VehycleRealm getVehycle() {
        return vehycle;
    }

    public void setVehycle(VehycleRealm vehycle) {
        this.vehycle = vehycle;
    }

    public TablePriceRealm getTablePrice() {
        return tablePrice;
    }

    public void setTablePrice(TablePriceRealm tablePrice) {
        this.tablePrice = tablePrice;
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

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
