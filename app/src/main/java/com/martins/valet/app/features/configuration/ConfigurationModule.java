package com.martins.valet.app.features.configuration;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.configuration.SyncInteractor;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 7/16/16.
 */
@Module
public class ConfigurationModule {

    @Provides
    @PerActivity
    @Named("sync")
    UseCase provideSyncInteractor(SyncInteractor interactor){
        return interactor;
    }

}
