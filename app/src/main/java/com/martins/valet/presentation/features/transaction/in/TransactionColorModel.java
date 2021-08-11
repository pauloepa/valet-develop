package com.martins.valet.presentation.features.transaction.in;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by policante on 7/10/16.
 */
public class TransactionColorModel implements Parcelable {

    private String title;
    private int color;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.color);
    }

    public TransactionColorModel() {
    }

    protected TransactionColorModel(Parcel in) {
        this.title = in.readString();
        this.color = in.readInt();
    }

    public static final Parcelable.Creator<TransactionColorModel> CREATOR = new Parcelable.Creator<TransactionColorModel>() {
        @Override
        public TransactionColorModel createFromParcel(Parcel source) {
            return new TransactionColorModel(source);
        }

        @Override
        public TransactionColorModel[] newArray(int size) {
            return new TransactionColorModel[size];
        }
    };
}
