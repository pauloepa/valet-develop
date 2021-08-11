package com.martins.valet.data.features.configuration.disk.realm;

import com.martins.valet.data.features.configuration.disk.ConfigurationDataSourceDisk;
import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.data.features.entity.disk.ConfigurationRealm;
import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;
import com.martins.valet.domain.features.mappers.realm.BrandVehycleRealmMapper;
import com.martins.valet.domain.features.mappers.realm.ConfigurationRealmMapper;
import com.martins.valet.domain.features.mappers.realm.TypeRealmMapper;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.features.model.TypeVehycle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by policante on 7/16/16.
 */
public class ConfigurationDataSourceDiskRealm implements ConfigurationDataSourceDisk {

    public ConfigurationDataSourceDiskRealm() {
    }

    @Override
    public Observable<List<TypeVehycle>> getAllTypes() {
        return Observable.create(new Observable.OnSubscribe<List<TypeVehycle>>() {
            @Override
            public void call(Subscriber<? super List<TypeVehycle>> subscriber) {
                Realm realm = null;
                try {
                    realm = Realm.getDefaultInstance();
                    RealmResults<TypeVehycleRealm> results = realm.where(TypeVehycleRealm.class).findAll();

                    List<TypeVehycle> types = new ArrayList<>();
                    for (TypeVehycleRealm type : results) {
                        types.add(new TypeRealmMapper().dataToModel(type));
                    }
                    subscriber.onNext(types);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    @Override
    public Observable<Boolean> saveTypes(final List<TypeVehycle> types) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                Realm realm = null;
                try {

                    realm = Realm.getDefaultInstance();
                    realm.beginTransaction();

                    realm.delete(TypeVehycleRealm.class);

                    for (TypeVehycle t : types) {
                        TypeVehycleRealm type = new TypeVehycleRealm();
                        type.setIdentifier(t.getIdentifier());
                        type.setType(t.getName());
                        type.setIcon(t.getIcon());
                        realm.copyToRealmOrUpdate(type);
                    }

                    realm.commitTransaction();

                    subscriber.onNext(true);
                    subscriber.onCompleted();

                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    @Override
    public Observable<List<BrandVehycle>> getAllBrands(final int typeId) {
        return Observable.create(new Observable.OnSubscribe<List<BrandVehycle>>() {
            @Override
            public void call(Subscriber<? super List<BrandVehycle>> subscriber) {
                Realm realm = null;
                try {
                    realm = Realm.getDefaultInstance();
                    RealmResults<BrandVehycleRealm> results = realm.where(BrandVehycleRealm.class).equalTo("type", String.valueOf(typeId)).findAll();

                    List<BrandVehycle> brands = new ArrayList<>();
                    for (BrandVehycleRealm brand : results) {
                        brands.add(new BrandVehycleRealmMapper().dataToModel(brand));
                    }

                    subscriber.onNext(brands);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    @Override
    public Observable<Boolean> saveBrands(final List<BrandVehycle> brands) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                Realm realm = null;
                try {

                    realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.delete(BrandVehycleRealm.class);
                    realm.commitTransaction();

                    for (BrandVehycle b : brands) {
                        realm.beginTransaction();
                        BrandVehycleRealm brand = new BrandVehycleRealm();
                        brand.setIdentifier(b.getIdentifier());
                        brand.setName(b.getName());
                        brand.setIcon(b.getIcon());
                        brand.setType(b.getType());
                        realm.copyToRealm(brand);
                        realm.commitTransaction();
                    }

                    subscriber.onNext(true);
                    subscriber.onCompleted();

                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    @Override
    public Observable<Configuration> getConfiguration() {
        return Observable.create(subscriber -> {
            Realm realm = null;
            try {
                realm = Realm.getDefaultInstance();
                ConfigurationRealm config = realm.where(ConfigurationRealm.class).findFirst();
                subscriber.onNext(new ConfigurationRealmMapper().dataToModel(config));
            } catch (Exception e) {
                subscriber.onError(e);
            } finally {
                if (realm != null) {
                    realm.close();
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Configuration> saveConfiguration(final Configuration configuration) {
        return Observable.create(subscriber -> {
            Realm realm = null;
            try {
                realm = Realm.getDefaultInstance();
                ConfigurationRealm config = realm.where(ConfigurationRealm.class).findFirst();
                if (config == null) {
                    config = new ConfigurationRealm();
                    config.setIdentifier(1);
                }

                realm.beginTransaction();
                config.setPrinterHost(configuration.getPrinterHost());
                config.setPrinterPort(9100);
                config.setAddress(configuration.getAddress());
                config.setCompany(configuration.getCompany());
                config.setDocument(configuration.getDocument());
                config.setPhone(configuration.getPhone());
                config.setUserToken(configuration.getUserToken());
                config.setObs(configuration.getObs());

                config = realm.copyToRealmOrUpdate(config);

                realm.commitTransaction();

                subscriber.onNext(new ConfigurationRealmMapper().dataToModel(config));
                subscriber.onCompleted();

            } catch (Exception e) {
                subscriber.onError(e);
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }
        });
    }
}
