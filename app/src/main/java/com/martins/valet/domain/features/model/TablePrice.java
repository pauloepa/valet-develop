package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by policante on 7/16/16.
 */
public class TablePrice implements Parcelable {

    private int identifier;
    private String name;
    private List<Price> prices;

    public TablePrice() {

    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.identifier);
        dest.writeString(this.name);
        dest.writeTypedList(this.prices);
    }

    protected TablePrice(Parcel in) {
        this.identifier = in.readInt();
        this.name = in.readString();
        this.prices = in.createTypedArrayList(Price.CREATOR);
    }

    public static final Parcelable.Creator<TablePrice> CREATOR = new Parcelable.Creator<TablePrice>() {
        @Override
        public TablePrice createFromParcel(Parcel source) {
            return new TablePrice(source);
        }

        @Override
        public TablePrice[] newArray(int size) {
            return new TablePrice[size];
        }
    };

    public float calculateValue(float timeInMinutes) {

        if (getPrices() == null) {
            return 0;
        }

        float timePrice = 0;
        float value = 0;

        int aux = 0;
        while (true){
            if (timeInMinutes <= timePrice){
                break;
            }

            timePrice += getPrices().get(aux).getTime();
            value += getPrices().get(aux).getValue();

            aux += 1;
            aux = Math.min( aux , getPrices().size() - 1 );
        }

        return value;
    }
}
