package com.martins.valet.data.features.configuration.disk;

import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.data.features.entity.disk.EnumTransactionType;
import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.TypeVehycle;

import java.util.List;

import rx.Observable;

/**
 * Created by policante on 7/16/16.
 */
public interface ConfigurationDataSourceDisk {

    Observable<List<TypeVehycle>> getAllTypes();

    Observable<Boolean> saveTypes(List<TypeVehycle> types);

    Observable<List<BrandVehycle>> getAllBrands(int typeId);

    Observable<Boolean> saveBrands(List<BrandVehycle> brands);

    Observable<Configuration> getConfiguration();

    Observable<Configuration> saveConfiguration(Configuration configuration);

}
