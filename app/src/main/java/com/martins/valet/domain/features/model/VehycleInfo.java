package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.w3c.dom.Text;

/**
 * Created by policante on 7/15/16.
 */
public class VehycleInfo implements Parcelable {

    private Client client;
    private Vehycle vehycle;
    private boolean existsTransactionOpen;

    public VehycleInfo() {

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehycle getVehycle() {
        return vehycle;
    }

    public void setVehycle(Vehycle vehycle) {
        this.vehycle = vehycle;
    }

    public String getObservation() {
        StringBuilder message = new StringBuilder();

        if (getClient() != null && !TextUtils.isEmpty(getClient().getObservation())) {
            message.append("- Cliente").append("\n");
            message.append(getClient().getObservation()).append("\n");
        }

        if (getVehycle() != null && !TextUtils.isEmpty(getVehycle().getObservation())) {
            message.append("- Veículo").append("\n");
            message.append(getVehycle().getObservation()).append("\n");
        }

        return message.toString();
    }

    public boolean isExistsTransactionOpen() {
        return existsTransactionOpen;
    }

    public void setExistsTransactionOpen(boolean existsTransactionOpen) {
        this.existsTransactionOpen = existsTransactionOpen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.client, flags);
        dest.writeParcelable(this.vehycle, flags);
        dest.writeByte(this.existsTransactionOpen ? (byte) 1 : (byte) 0);
    }

    protected VehycleInfo(Parcel in) {
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.vehycle = in.readParcelable(Vehycle.class.getClassLoader());
        this.existsTransactionOpen = in.readByte() != 0;
    }

    public static final Parcelable.Creator<VehycleInfo> CREATOR = new Parcelable.Creator<VehycleInfo>() {
        @Override
        public VehycleInfo createFromParcel(Parcel source) {
            return new VehycleInfo(source);
        }

        @Override
        public VehycleInfo[] newArray(int size) {
            return new VehycleInfo[size];
        }
    };
}
