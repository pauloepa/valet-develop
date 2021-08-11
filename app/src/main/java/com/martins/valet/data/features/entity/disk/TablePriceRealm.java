package com.martins.valet.data.features.entity.disk;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by policante on 7/15/16.
 */
public class TablePriceRealm extends RealmObject {

    private int identifier;
    private String name;
    private RealmList<PriceRealm> prices;

    public TablePriceRealm() {
        prices = new RealmList<>();
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

    public RealmList<PriceRealm> getPrices() {
        return prices;
    }

    public void setPrices(RealmList<PriceRealm> prices) {
        this.prices = prices;
    }
}
