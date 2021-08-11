package com.martins.valet.app.features.configuration.printer;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.configuration.SaveConfigurationInteractor;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 7/18/16.
 */
@Module
public class ConfigPrinterModule {

    @PerActivity
    @Provides
    @Named("printer")
    UseCase providePrinterInteractor(PrinterInteractor interactor){
        return interactor;
    }

    @PerActivity
    @Provides
    @Named("saveConfig")
    UseCase provideSaveConfigInteractor(SaveConfigurationInteractor interactor){
        return interactor;
    }



}
