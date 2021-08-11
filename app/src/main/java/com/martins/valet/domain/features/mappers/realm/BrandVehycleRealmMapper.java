package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.domain.features.model.BrandVehycle;

/**
 * Created by policante on 7/16/16.
 */
public class BrandVehycleRealmMapper implements Mapper<BrandVehycle,BrandVehycleRealm> {

    public BrandVehycleRealmMapper() {
    }

    @Override
    public BrandVehycleRealm modelToData(BrandVehycle model) {
        if (model == null) {
            return null;
        }

        BrandVehycleRealm data = new BrandVehycleRealm();
        data.setIdentifier(model.getIdentifier());
        data.setName(model.getName());
        data.setIcon(model.getIcon());

        return data;
    }

    @Override
    public BrandVehycle dataToModel(BrandVehycleRealm data) {
        if (data == null){
            return null;
        }

        BrandVehycle model = new BrandVehycle();
        model.setIdentifier(data.getIdentifier());
        model.setIcon(data.getIcon());
        model.setName(data.getName());

        return model;
    }
}
