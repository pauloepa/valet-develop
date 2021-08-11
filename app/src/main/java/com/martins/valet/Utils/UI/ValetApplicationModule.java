package com.martins.valet.Utils.UI;

import android.content.Context;

import com.martins.valet.Utils.Helpers.Navigator;
import com.martins.valet.ValetApplication;
import com.martins.valet.data.features.configuration.ConfigurationDataRepository;
import com.martins.valet.data.features.configuration.cloud.ConfigurationDataSourceCloud;
import com.martins.valet.data.features.configuration.cloud.rest.ConfigurationDataServiceRetrofit;
import com.martins.valet.data.features.configuration.disk.ConfigurationDataSourceDisk;
import com.martins.valet.data.features.configuration.disk.realm.ConfigurationDataSourceDiskRealm;
import com.martins.valet.data.features.transaction.TransactionDataRepository;
import com.martins.valet.data.features.transaction.cloud.TransactionDataSourceCloud;
import com.martins.valet.data.features.transaction.cloud.rest.TransactionDataServiceRetrofit;
import com.martins.valet.data.features.transaction.disk.TransactionDataSourceDisk;
import com.martins.valet.data.features.transaction.disk.realm.TransactionDataSourceDiskRealm;
import com.martins.valet.domain.features.configuration.ConfigurationRepository;
import com.martins.valet.domain.features.transaction.TransactionRepository;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.presentation.features.configuration.ConfigManager;
import com.martins.valet.presentation.features.home.HomeMenuManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by policante on 7/2/16.
 */
@Module
public class ValetApplicationModule {

    private final Context context;
    private final ValetApplication application;

    public ValetApplicationModule(Context context, ValetApplication application) {
        this.context = context;
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    HomeMenuManager provideHomeMenuManager() {
        return new HomeMenuManager(this.context);
    }

    @Provides
    @Singleton
    ConfigManager provideConfigManager(){
        return new ConfigManager();
    }

//    TRANSACTIONS

    @Provides
    @Singleton
    TransactionRepository provideTransactionRepository(TransactionDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    TransactionDataSourceCloud provideTransactionDataSourceCloud(){
        return new TransactionDataServiceRetrofit();
    }

    @Provides
    @Singleton
    TransactionDataSourceDisk provideTransactionDataSourceDisk(){
        return new TransactionDataSourceDiskRealm();
    }

//    CONFIGURATION

    @Provides
    @Singleton
    ConfigurationRepository provideConfigurationRepository(ConfigurationDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    ConfigurationDataSourceDisk provideConfigurationDataSourceDisk(){
        return new ConfigurationDataSourceDiskRealm();
    }

    @Provides
    @Singleton
    ConfigurationDataSourceCloud provideConfigurationDataSourceCloud(){
        return new ConfigurationDataServiceRetrofit();
    }

    @Provides
    @Singleton
    ObserveOn provideObserveOn() {
        return new ObserveOn() {
            @Override
            public Scheduler getScheduler() {
                return AndroidSchedulers.mainThread();
            }
        };
    }

    @Provides
    @Singleton
    SubscriberOn provideSubscriberOn() {
        return new SubscriberOn() {
            @Override
            public Scheduler getScheduler() {
                return Schedulers.newThread();
            }
        };
    }
}
