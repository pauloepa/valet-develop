package com.martins.valet.domain.features.mappers.realm;

import com.martins.valet.Utils.Helpers.Mapper;
import com.martins.valet.data.features.entity.disk.ConfigurationRealm;
import com.martins.valet.domain.features.model.Configuration;

/**
 * Created by policante on 7/18/16.
 */
public class ConfigurationRealmMapper implements Mapper<Configuration, ConfigurationRealm> {

    @Override
    public ConfigurationRealm modelToData(Configuration model) {
        if (model == null) {
            return null;
        }

        ConfigurationRealm data = new ConfigurationRealm();
        data.setPrinterHost(model.getPrinterHost());
        data.setPrinterPort(model.getPrinterPort());
        data.setAddress(model.getAddress());
        data.setCompany(model.getCompany());
        data.setDocument(model.getCompany());
        data.setObs(model.getObs());
        data.setPhone(model.getPhone());
        data.setUserToken(model.getUserToken());

        return data;
    }

    @Override
    public Configuration dataToModel(ConfigurationRealm data) {
        if (data == null) {
            return null;
        }

        Configuration model = new Configuration();
        model.setPrinterHost(data.getPrinterHost());
        model.setPrinterPort(data.getPrinterPort());
        model.setAddress(data.getAddress());
        model.setCompany(data.getCompany());
        model.setDocument(data.getDocument());
        model.setObs(data.getObs());
        model.setPhone(data.getPhone());
        model.setUserToken(data.getUserToken());

        return model;
    }
}
