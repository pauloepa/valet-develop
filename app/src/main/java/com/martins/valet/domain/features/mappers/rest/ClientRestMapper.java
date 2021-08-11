package com.martins.valet.domain.features.mappers.rest;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.rest.ClientResponse;
import com.martins.valet.domain.features.model.Client;
import com.martins.valet.domain.features.model.ClientType;

/**
 * Created by policante on 7/26/16.
 */
public class ClientRestMapper implements Mapper<Client, ClientResponse> {
    @Override
    public ClientResponse modelToData(Client model) {
        if (model == null){
            return null;
        }

        ClientResponse data = new ClientResponse();
        data.setIdentifier(model.getIdentifier());
        data.setDocument(model.getDocument());
        data.setName(model.getName());
        data.setObservation(model.getObservation());

        return data;
    }

    @Override
    public Client dataToModel(ClientResponse data) {
        if (data == null){
            return null;
        }

        Client model = new Client();
        model.setIdentifier(data.getIdentifier());
        model.setDocument(data.getDocument());
        model.setName(data.getName());
        model.setObservation(data.getObservation());

        return model;
    }
}
