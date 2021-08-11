package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.PriceRealm;
import com.martins.valet.data.features.entity.disk.TablePriceRealm;
import com.martins.valet.domain.features.model.Price;
import com.martins.valet.domain.features.model.TablePrice;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by policante on 7/16/16.
 */
public class TablePriceRealmMapper implements Mapper<TablePrice, TablePriceRealm> {

    @Override
    public TablePriceRealm modelToData(TablePrice model) {
        if (model == null){
            return null;
        }

        TablePriceRealm data = new TablePriceRealm();
        data.setIdentifier(model.getIdentifier());
        data.setName(model.getName());

        RealmList<PriceRealm> priceRealms = new RealmList<>();
        for (Price price : model.getPrices()) {
            priceRealms.add(new PriceRealmMapper().modelToData(price));
        }

        data.setPrices(priceRealms);

        return data;
    }

    @Override
    public TablePrice dataToModel(TablePriceRealm data) {
        if (data == null){
            return null;
        }

        TablePrice model = new TablePrice();
        model.setIdentifier(data.getIdentifier());
        model.setName(data.getName());

        List<Price> priceRealms = new ArrayList<>();
        for (PriceRealm price : data.getPrices()) {
            priceRealms.add(new PriceRealmMapper().dataToModel(price));
        }

        model.setPrices(priceRealms);

        return model;
    }
}
