package com.martins.valet.data.features.configuration.cloud;

import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.TypeVehycle;

import java.util.List;

import rx.Observable;

/**
 * Created by policante on 7/24/16.
 */
public interface ConfigurationDataSourceCloud {

    Observable<List<BrandVehycle>> getBrands(String token);

    Observable<List<BrandVehycle>> getBrands(String token, int typeID);

    Observable<List<TypeVehycle>> getTypes(String token);

    Observable<String> doLogin(String username, String password);

}
