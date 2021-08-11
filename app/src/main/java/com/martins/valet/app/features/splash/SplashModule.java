package com.martins.valet.app.features.splash;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.configuration.SyncInteractor;
import com.martins.valet.domain.features.splash.LoadConfigInteractor;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 7/3/16.
 */
@Module
public class SplashModule {

    @Provides
    @PerActivity
    @Named("loadConfig")
    UseCase provideLoadConfigurationInteractor(LoadConfigInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("sync")
    UseCase provideSyncInteractor(SyncInteractor interactor){
        return interactor;
    }

}
