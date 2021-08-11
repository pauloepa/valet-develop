package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.PriceRealm;
import com.martins.valet.domain.features.model.Price;

/**
 * Created by policante on 7/16/16.
 */
public class PriceRealmMapper implements Mapper<Price, PriceRealm> {

    @Override
    public PriceRealm modelToData(Price model) {
        if (model == null){
            return null;
        }

        PriceRealm data = new PriceRealm();
        data.setIdentifier(model.getIdentifier());
        data.setValue(model.getValue());
        data.setTime(model.getTime());

        return data;
    }

    @Override
    public Price dataToModel(PriceRealm data) {
        if (data == null){
            return null;
        }

        Price model = new Price();
        model.setIdentifier(data.getIdentifier());
        model.setValue(data.getValue());
        model.setTime(data.getTime());

        return model;
    }
}
