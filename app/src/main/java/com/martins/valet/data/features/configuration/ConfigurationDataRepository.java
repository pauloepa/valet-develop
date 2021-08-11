package com.martins.valet.data.features.configuration;

import com.martins.valet.data.features.configuration.cloud.ConfigurationDataSourceCloud;
import com.martins.valet.data.features.configuration.disk.ConfigurationDataSourceDisk;
import com.martins.valet.domain.features.configuration.ConfigurationRepository;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.features.model.TypeVehycle;
import com.martins.valet.presentation.features.configuration.ConfigManager;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by policante on 7/16/16.
 */
public class ConfigurationDataRepository implements ConfigurationRepository {

    private final ConfigManager configManager;
    private final ConfigurationDataSourceDisk dataSourceDisk;
    private final ConfigurationDataSourceCloud dataSourceCloud;

    @Inject
    public ConfigurationDataRepository(ConfigManager configManager, ConfigurationDataSourceDisk dataSourceDisk, ConfigurationDataSourceCloud dataSourceCloud) {
        this.configManager = configManager;
        this.dataSourceDisk = dataSourceDisk;
        this.dataSourceCloud = dataSourceCloud;
    }

    @Override
    public Observable<Boolean> sync() {
        return dataSourceCloud.getTypes(configManager.getConfiguration().getUserToken())
                .flatMap((Func1<List<TypeVehycle>, Observable<Boolean>>) dataSourceDisk::saveTypes)
                .flatMap((Func1<Boolean, Observable<Boolean>>) aBoolean -> dataSourceCloud.getBrands(configManager.getConfiguration().getUserToken())
                        .flatMap((Func1<List<BrandVehycle>, Observable<Boolean>>) dataSourceDisk::saveBrands));
    }

    @Override
    public Observable<List<TypeVehycle>> getAllTypes() {
        return dataSourceDisk.getAllTypes();
//        return dataSourceCloud.getTypes();
    }

    @Override
    public Observable<List<BrandVehycle>> getAllBrands(int typeId) {
//        return dataSourceCloud.getBrands(typeId);
        return dataSourceDisk.getAllBrands(typeId);
    }

    @Override
    public Observable<Configuration> getConfiguration() {
        return dataSourceDisk.getConfiguration();
    }

    @Override
    public Observable<Configuration> saveConfiguration(Configuration configuration) {
        return this.dataSourceDisk.saveConfiguration(configuration);
    }

    @Override
    public Observable<String> doLogin(String username, String password) {
        return this.dataSourceCloud.doLogin(username, password);
    }
}
