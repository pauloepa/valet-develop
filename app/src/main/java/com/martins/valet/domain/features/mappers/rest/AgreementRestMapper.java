package com.martins.valet.domain.features.mappers.rest;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.AgreementRest;
import com.martins.valet.domain.features.model.Agreement;

/**
 * Created by policante on 8/4/16.
 */
public class AgreementRestMapper implements Mapper<Agreement, AgreementRest> {

    @Override
    public AgreementRest modelToData(Agreement model) {
        if (model == null) {
            return null;
        }

        AgreementRest data = new AgreementRest();
        data.setIdentifier(model.getIdentifier());
        data.setName(model.getName());
        data.setType(model.getType());
        data.setValue(model.getValue());

        return data;
    }

    @Override
    public Agreement dataToModel(AgreementRest data) {
        if (data == null) {
            return null;
        }

        Agreement model = new Agreement();
        model.setIdentifier(data.getIdentifier());
        model.setName(data.getName());
        model.setType(data.getType());
        model.setValue(data.getValue());

        return model;
    }
}
