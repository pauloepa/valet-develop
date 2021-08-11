package com.martins.valet.domain.features.mappers.rest;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;
import com.martins.valet.data.features.entity.rest.TypeVehycleRest;
import com.martins.valet.domain.features.model.TypeVehycle;

import javax.inject.Inject;

/**
 * Created by policante on 7/26/16.
 */
public class TypeRestMapper implements Mapper<TypeVehycle, TypeVehycleRest> {

    @Override
    public TypeVehycleRest modelToData(TypeVehycle model) {
        if (model == null){
            return null;
        }

        TypeVehycleRest data = new TypeVehycleRest();
        data.setIdentifier(String.valueOf(model.getIdentifier()));
        data.setType(model.getName());
        data.setIcon(model.getIcon());

        return data;
    }

    @Override
    public TypeVehycle dataToModel(TypeVehycleRest data) {
        if (data == null){
            return null;
        }

        TypeVehycle model = new TypeVehycle();
        model.setIdentifier(Integer.parseInt(data.getIdentifier()));
        model.setName(data.getType());
        model.setIcon(data.getIcon());

        return model;
    }
}
