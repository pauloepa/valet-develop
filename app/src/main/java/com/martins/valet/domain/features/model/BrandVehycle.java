package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by policante on 7/16/16.
 */
public class BrandVehycle implements Parcelable {

    private int identifier;
    private String name;
    private String icon;
    private String type;

    public BrandVehycle() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.identifier);
        dest.writeString(this.name);
        dest.writeString(this.icon);
        dest.writeString(this.type);
    }

    protected BrandVehycle(Parcel in) {
        this.identifier = in.readInt();
        this.name = in.readString();
        this.icon = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<BrandVehycle> CREATOR = new Parcelable.Creator<BrandVehycle>() {
        @Override
        public BrandVehycle createFromParcel(Parcel source) {
            return new BrandVehycle(source);
        }

        @Override
        public BrandVehycle[] newArray(int size) {
            return new BrandVehycle[size];
        }
    };
}
