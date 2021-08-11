package com.martins.valet.data.features.entity.disk;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by policante on 7/15/16.
 */
public class TypeVehycleRealm extends RealmObject {

    @PrimaryKey
    private int identifier;
    private String type;
    private String icon;

    public TypeVehycleRealm() {

    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
