package com.martins.valet.app.features.reports.revenuew;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.features.reports.ReportsRevenuewInteractor;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 8/13/16.
 */
@Module
public class ReportsRevenuewModule {

    @Provides
    @PerActivity
    @Named("revenuew")
    UseCase provideReportsRevenuewInteractor(ReportsRevenuewInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("printer")
    UseCase providePrinterInteractor(PrinterInteractor interactor){
        return interactor;
    }

}
