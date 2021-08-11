package com.martins.valet.data.features.entity.disk;

import com.martins.valet.domain.features.model.ClientType;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by policante on 7/15/16.
 */
public class ClientRealm extends RealmObject{

    private int identifier;
    private String document;
    private String name;
    private String observation;
    private String type;
    private RealmList<VehycleRealm> vehycles;
    private TablePriceRealm tablePrice;

    public ClientRealm() {
        vehycles = new RealmList<>();
        type = ClientTypeRealm.NORMAL.toString();
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

    public RealmList<VehycleRealm> getVehycles() {
        return vehycles;
    }

    public void setVehycles(RealmList<VehycleRealm> vehycles) {
        this.vehycles = vehycles;
    }

    public TablePriceRealm getTablePrice() {
        return tablePrice;
    }

    public void setTablePrice(TablePriceRealm tablePrice) {
        this.tablePrice = tablePrice;
    }

    /**
     * use getClientType()
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Use setClientType(ClientTypeRealm)
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    public ClientTypeRealm getClientType() {
        if (getType() == null){
            return null;
        }
        return ClientTypeRealm.valueOf(getType());
    }

    public void setClientType(ClientTypeRealm clientType) {
        if (clientType == null){
            setType(null);
        }else{
            setType(clientType.toString());
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
