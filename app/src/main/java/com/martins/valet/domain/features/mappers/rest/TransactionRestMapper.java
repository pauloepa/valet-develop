package com.martins.valet.domain.features.mappers.rest;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.rest.TransactionInRest;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.Vehycle;

/**
 * Created by policante on 7/27/16.
 */
public class TransactionRestMapper implements Mapper<Transaction, TransactionInRest>{

    @Override
    public TransactionInRest modelToData(Transaction model) {
        if (model == null){
            return null;
        }

        TransactionInRest data = new TransactionInRest();
        data.setPlate(model.getVehycle().getPlate());
        data.setBrand(model.getVehycle().getBrand());
        data.setColor(model.getVehycle().getColor());
        data.setType(model.getVehycle().getType());
        data.setCreated(model.getTransactionIn());

        return data;
    }

    @Override
    public Transaction dataToModel(TransactionInRest data) {
        return null;
    }
}
