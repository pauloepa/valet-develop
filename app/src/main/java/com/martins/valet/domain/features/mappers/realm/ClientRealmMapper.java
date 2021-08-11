package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.ClientRealm;
import com.martins.valet.data.features.entity.disk.ClientTypeRealm;
import com.martins.valet.data.features.entity.disk.VehycleRealm;
import com.martins.valet.domain.features.model.Client;
import com.martins.valet.domain.features.model.ClientType;
import com.martins.valet.domain.features.model.Vehycle;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by policante on 7/16/16.
 */
public class ClientRealmMapper implements Mapper<Client, ClientRealm> {

    @Override
    public ClientRealm modelToData(Client model) {
        if (model == null){
            return null;
        }

        ClientRealm data = new ClientRealm();
        data.setIdentifier(model.getIdentifier());
        data.setDocument(model.getDocument());
        data.setName(model.getName());
        data.setClientType(ClientTypeRealm.from(model.getType()));
        data.setTablePrice(new TablePriceRealmMapper().modelToData(model.getTablePrice()));
        data.setObservation(model.getObservation());

        RealmList<VehycleRealm> vehycleRealms = new RealmList<>();
        for (Vehycle v : model.getVehycles()) {
            vehycleRealms.add( new VehycleRealmMapper().modelToData(v) );
        }
        data.setVehycles(vehycleRealms);

        return data;
    }

    @Override
    public Client dataToModel(ClientRealm data) {
        if (data == null){
            return null;
        }

        Client model = new Client();
        model.setIdentifier(data.getIdentifier());
        model.setDocument(data.getDocument());
        model.setName(data.getName());
        model.setType(ClientType.from(data.getClientType()));
        model.setTablePrice(new TablePriceRealmMapper().dataToModel(data.getTablePrice()));
        model.setObservation(data.getObservation());

        List<Vehycle> vehycles = new ArrayList<>();
        for (VehycleRealm v : data.getVehycles()) {
            vehycles.add( new VehycleRealmMapper().dataToModel(v) );
        }
        model.setVehycles(vehycles);

        return model;
    }
}
