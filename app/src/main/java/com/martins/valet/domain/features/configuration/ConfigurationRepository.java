package com.martins.valet.domain.features.configuration;

import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.features.model.TypeVehycle;

import java.util.List;

import rx.Observable;

/**
 * Created by policante on 7/16/16.
 */
public interface ConfigurationRepository {

    Observable<Boolean> sync();

    Observable<List<TypeVehycle>> getAllTypes();

    Observable<List<BrandVehycle>> getAllBrands(int typeId);

    Observable<Configuration> getConfiguration();

    Observable<Configuration> saveConfiguration(Configuration configuration);

    Observable<String> doLogin(String username, String password);
}
