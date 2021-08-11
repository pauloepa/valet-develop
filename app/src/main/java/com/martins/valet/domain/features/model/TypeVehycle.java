package com.martins.valet.domain.features.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by policante on 7/16/16.
 */
public class TypeVehycle implements Parcelable {

    private int identifier;
    private String name;
    private String icon;

    public TypeVehycle() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.identifier);
        dest.writeString(this.name);
        dest.writeString(this.icon);
    }

    protected TypeVehycle(Parcel in) {
        this.identifier = in.readInt();
        this.name = in.readString();
        this.icon = in.readString();
    }

    public static final Parcelable.Creator<TypeVehycle> CREATOR = new Parcelable.Creator<TypeVehycle>() {
        @Override
        public TypeVehycle createFromParcel(Parcel source) {
            return new TypeVehycle(source);
        }

        @Override
        public TypeVehycle[] newArray(int size) {
            return new TypeVehycle[size];
        }
    };
}
