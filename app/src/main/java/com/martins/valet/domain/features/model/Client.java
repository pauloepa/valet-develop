package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.martins.valet.data.features.entity.disk.TablePriceRealm;
import com.martins.valet.data.features.entity.disk.VehycleRealm;

import java.util.List;

/**
 * Created by policante on 7/15/16.
 */
public class Client implements Parcelable {

    private int identifier;
    private String document;
    private String name;
    private String observation;
    private ClientType type;
    private List<Vehycle> vehycles;
    private TablePrice tablePrice;

    public Client() {

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

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.identifier);
        dest.writeString(this.document);
        dest.writeString(this.name);
        dest.writeString(this.observation);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
    }

    protected Client(Parcel in) {
        this.identifier = in.readInt();
        this.document = in.readString();
        this.name = in.readString();
        this.observation = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : ClientType.values()[tmpType];
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public TablePrice getTablePrice() {
        return tablePrice;
    }

    public void setTablePrice(TablePrice tablePrice) {
        this.tablePrice = tablePrice;
    }

    public List<Vehycle> getVehycles() {
        return vehycles;
    }

    public void setVehycles(List<Vehycle> vehycles) {
        this.vehycles = vehycles;
    }
}
