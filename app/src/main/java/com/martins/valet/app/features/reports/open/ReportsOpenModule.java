package com.martins.valet.app.features.reports.open;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.features.reports.ReportsOpenInteractor;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 7/30/16.
 */
@Module
public class ReportsOpenModule {

    @Provides
    @PerActivity
    @Named("reportsOpen")
    UseCase provideReportsOpenInteractor(ReportsOpenInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("printer")
    UseCase providePrinterInteractor(PrinterInteractor interactor){
        return interactor;
    }

}
