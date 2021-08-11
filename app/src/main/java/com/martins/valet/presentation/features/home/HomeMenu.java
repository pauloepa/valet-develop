package com.martins.valet.presentation.features.home;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;

/**
 * Created by policante on 7/5/16.
 */
public class HomeMenu implements Parcelable {

    public static final Parcelable.Creator<HomeMenu> CREATOR = new Parcelable.Creator<HomeMenu>() {
        @Override
        public HomeMenu createFromParcel(Parcel source) {
            return new HomeMenu(source);
        }

        @Override
        public HomeMenu[] newArray(int size) {
            return new HomeMenu[size];
        }
    };
    private String title;
    private @DrawableRes int icon;
    private boolean enabled = true;

    public HomeMenu() {
    }

    protected HomeMenu(Parcel in) {
        this.title = in.readString();
        this.icon = in.readInt();
        this.enabled = in.readByte() != 0;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.icon);
        dest.writeByte(this.enabled ? (byte) 1 : (byte) 0);
    }
}
