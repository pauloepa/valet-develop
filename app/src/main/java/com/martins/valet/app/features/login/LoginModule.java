package com.martins.valet.app.features.login;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.configuration.DoLoginInteractor;
import com.martins.valet.domain.features.configuration.SaveConfigurationInteractor;
import com.martins.valet.domain.features.configuration.SyncInteractor;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 7/3/16.
 */
@Module
public class LoginModule {

    @Provides
    @PerActivity
    @Named("saveConfig")
    UseCase provideSaveConfig(SaveConfigurationInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("doLogin")
    UseCase provideLoginInteractor(DoLoginInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("sync")
    UseCase provideSyncInteractor(SyncInteractor interactor) {
        return interactor;
    }

}
