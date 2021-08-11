package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;
import com.martins.valet.domain.features.model.TypeVehycle;

/**
 * Created by policante on 7/16/16.
 */
public class TypeRealmMapper implements Mapper<TypeVehycle, TypeVehycleRealm> {

    @Override
    public TypeVehycleRealm modelToData(TypeVehycle model) {
        if (model == null){
            return null;
        }

        TypeVehycleRealm data = new TypeVehycleRealm();
        data.setIdentifier(model.getIdentifier());
        data.setType(model.getName());
        data.setIcon(model.getIcon());

        return data;
    }

    @Override
    public TypeVehycle dataToModel(TypeVehycleRealm data) {
        if (data == null){
            return null;
        }

        TypeVehycle model = new TypeVehycle();
        model.setIdentifier(data.getIdentifier());
        model.setName(data.getType());
        model.setIcon(data.getIcon());

        return model;
    }
}
