package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.VehycleRealm;
import com.martins.valet.domain.features.model.Vehycle;

/**
 * Created by policante on 7/16/16.
 */
public class VehycleRealmMapper implements Mapper<Vehycle, VehycleRealm> {
    @Override
    public VehycleRealm modelToData(Vehycle model) {
        if (model == null){
            return null;
        }

        VehycleRealm data = new VehycleRealm();
        data.setPlate(model.getPlate());
//        data.setBrand( model.getBrand());
        data.setColor(model.getColor());
        data.setObservation(model.getObservation());

        return data;
    }

    @Override
    public Vehycle dataToModel(VehycleRealm data) {
        if (data == null){
            return null;
        }

        Vehycle model = new Vehycle();
        model.setPlate(data.getPlate());
//        model.setBrand( new BrandVehycleRealmMapper().dataToModel(data.getBrand()));
        model.setColor(data.getColor());
        model.setObservation(data.getObservation());

        return model;
    }
}
