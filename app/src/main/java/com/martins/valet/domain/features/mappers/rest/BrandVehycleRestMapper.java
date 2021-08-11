package com.martins.valet.domain.features.mappers.rest;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.rest.BrandVehycleRest;
import com.martins.valet.domain.features.model.BrandVehycle;

/**
 * Created by policante on 7/24/16.
 */
public class BrandVehycleRestMapper implements Mapper<BrandVehycle, BrandVehycleRest> {
    @Override
    public BrandVehycleRest modelToData(BrandVehycle model) {
        if (model == null) {
            return null;
        }

        BrandVehycleRest data = new BrandVehycleRest();
        data.setIdentifier(model.getIdentifier());
        data.setName(model.getName());
        data.setIcon(model.getIcon());
        data.setType(model.getType());

        return data;
    }

    @Override
    public BrandVehycle dataToModel(BrandVehycleRest data) {
        if (data == null) {
            return null;
        }

        BrandVehycle model = new BrandVehycle();
        model.setIdentifier(data.getIdentifier());
        model.setName(data.getName());
        model.setIcon(data.getIcon());
        model.setType(data.getType());

        return model;
    }
}
