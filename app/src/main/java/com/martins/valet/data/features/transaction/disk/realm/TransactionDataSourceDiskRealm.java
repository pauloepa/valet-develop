package com.martins.valet.data.features.transaction.disk.realm;

import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.data.features.entity.disk.ClientRealm;
import com.martins.valet.data.features.entity.disk.TablePriceRealm;
import com.martins.valet.data.features.entity.disk.TransactionRealm;
import com.martins.valet.data.features.entity.disk.VehycleRealm;
import com.martins.valet.data.features.transaction.disk.TransactionDataSourceDisk;
import com.martins.valet.domain.features.mappers.realm.BrandVehycleRealmMapper;
import com.martins.valet.domain.features.mappers.realm.TablePriceRealmMapper;
import com.martins.valet.domain.features.mappers.realm.TransactionRealmMapper;
import com.martins.valet.domain.features.model.Client;
import com.martins.valet.domain.features.model.ClientType;
import com.martins.valet.domain.features.model.Vehycle;
import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.domain.features.model.Transaction;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by policante on 7/13/16.
 */
public class TransactionDataSourceDiskRealm implements TransactionDataSourceDisk {

    public TransactionDataSourceDiskRealm() {
    }
}