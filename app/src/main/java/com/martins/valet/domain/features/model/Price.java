package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by policante on 7/16/16.
 */
public class Price implements Parcelable {

    private int identifier;
    private int time;
    private float value;

    public Price() {
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.identifier);
        dest.writeInt(this.time);
        dest.writeFloat(this.value);
    }

    protected Price(Parcel in) {
        this.identifier = in.readInt();
        this.time = in.readInt();
        this.value = in.readFloat();
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
