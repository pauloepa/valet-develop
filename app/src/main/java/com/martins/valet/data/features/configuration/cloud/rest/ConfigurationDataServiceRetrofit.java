package com.martins.valet.data.features.configuration.cloud.rest;

import com.martins.valet.data.features.configuration.cloud.ConfigurationDataSourceCloud;
import com.martins.valet.data.features.entity.rest.BrandVehycleRest;
import com.martins.valet.data.features.entity.rest.TypeVehycleRest;
import com.martins.valet.data.helpers.rest.ValetRestServiceRetrofit;
import com.martins.valet.domain.features.mappers.rest.BrandVehycleRestMapper;
import com.martins.valet.domain.features.mappers.rest.TypeRestMapper;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.TypeVehycle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by policante on 7/24/16.
 */
public class ConfigurationDataServiceRetrofit extends ValetRestServiceRetrofit implements ConfigurationDataSourceCloud {

    private TypeRestMapper typeMapper;
    private BrandVehycleRestMapper brandMapper;

    public ConfigurationDataServiceRetrofit() {
        typeMapper = new TypeRestMapper();
        brandMapper = new BrandVehycleRestMapper();
    }

    private ConfigurationRestService getService() {
        return getService(ConfigurationRestService.class);
    }

    @Override
    public Observable<List<BrandVehycle>> getBrands(String token) {
        return getService().getAllBrands(token)
                .map(brandVehycleRests -> {
                    List<BrandVehycle> list = new ArrayList<>();
                    for (BrandVehycleRest rest : brandVehycleRests) {
                        list.add(brandMapper.dataToModel(rest));
                    }
                    return list;
                });
    }

    @Override
    public Observable<List<BrandVehycle>> getBrands(String token, final int typeID) {
        return getService().getAllBrands(token)
                .switchMap(brandVehycleRests -> Observable.from(brandVehycleRests)
                        .filter(brandVehycleRest -> brandVehycleRest.getType().equals(String.valueOf(typeID))).toList())
                .map(brandVehycleRests -> {
                    List<BrandVehycle> list = new ArrayList<>();
                    for (BrandVehycleRest rest : brandVehycleRests) {
                        list.add(brandMapper.dataToModel(rest));
                    }
                    return list;
                });
    }

    @Override
    public Observable<List<TypeVehycle>> getTypes(String token) {
        return getService().getAllTypes(token).map(typeVehycleRests -> {
            List<TypeVehycle> list = new ArrayList<>();
            for (TypeVehycleRest rest : typeVehycleRests) {
                list.add(typeMapper.dataToModel(rest));
            }
            return list;
        });
    }

    @Override
    public Observable<String> doLogin(String username, String password) {
        return getService().doLogin(username, password).flatMap((Func1<String, Observable<String>>) s -> {
            if (s == null || s.isEmpty()) {
                return Observable.error(new Exception("Usuário ou senha inválidos"));
            }
            return Observable.just(s);
        });
    }
}
