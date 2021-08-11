package com.martins.valet.domain.features.mappers.rest;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.rest.VehycleResponse;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.Vehycle;

/**
 * Created by policante on 7/26/16.
 */
public class VehycleResponseMapper implements Mapper<Vehycle, VehycleResponse> {

    @Override
    public VehycleResponse modelToData(Vehycle model) {
        if (model == null) {
            return null;
        }

        VehycleResponse data = new VehycleResponse();
        data.setPlate(model.getPlate());
        data.setType(model.getType());
        data.setBrand(model.getBrand());
        data.setColor(model.getColor());
        data.setObservation(model.getObservation());

        return data;
    }

    @Override
    public Vehycle dataToModel(VehycleResponse data) {
        if (data == null){
            return null;
        }

        Vehycle model = new Vehycle();
        model.setPlate(data.getPlate());
        model.setColor(data.getColor());
        model.setObservation(data.getObservation());
        model.setType(data.getType());
        model.setBrand(data.getBrand());

        return model;
    }
}
