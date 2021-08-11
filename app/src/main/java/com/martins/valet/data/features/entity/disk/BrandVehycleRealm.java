package com.martins.valet.data.features.entity.disk;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by policante on 7/15/16.
 */
public class BrandVehycleRealm extends RealmObject {

    @PrimaryKey
    private int identifier;
    private String name;
    private String icon;
    private String type;

    public BrandVehycleRealm() {
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
}
