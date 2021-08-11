package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.TransactionRealm;
import com.martins.valet.domain.features.model.Transaction;

/**
 * Created by policante on 7/16/16.
 */
public class TransactionRealmMapper implements Mapper<Transaction, TransactionRealm> {
    @Override
    public TransactionRealm modelToData(Transaction model) {
        if (model == null){
            return null;
        }

        TransactionRealm data = new TransactionRealm();
        data.setIdentifier(model.getIdentifier());
        data.setClient(new ClientRealmMapper().modelToData(model.getClient()));
        data.setVehycle(new VehycleRealmMapper().modelToData(model.getVehycle()));
        data.setTransactionIn(model.getTransactionIn());
        data.setTransactionOut(model.getTransactionOut());
        data.setSyncronizedIn(model.getSyncronizedIn());
        data.setSyncronizedOut(model.getSyncronizedOut());
        data.setTimeTotal(model.getTimeTotal());
        data.setValueTotal(model.getValueTotal());

        return data;
    }

    @Override
    public Transaction dataToModel(TransactionRealm data) {
        if (data == null){
            return null;
        }

        Transaction model = new Transaction();
        model.setIdentifier(data.getIdentifier());
        model.setClient(new ClientRealmMapper().dataToModel(data.getClient()));
        model.setVehycle(new VehycleRealmMapper().dataToModel(data.getVehycle()));
        model.setTransactionIn(data.getTransactionIn());
        model.setTransactionOut(data.getTransactionOut());
        model.setSyncronizedIn(data.getSyncronizedIn());
        model.setSyncronizedOut(data.getSyncronizedOut());
        model.setTimeTotal(data.getTimeTotal());
        model.setValueTotal(data.getValueTotal());

        return model;
    }
}
