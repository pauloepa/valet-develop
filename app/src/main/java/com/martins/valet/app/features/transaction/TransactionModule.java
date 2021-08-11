package com.martins.valet.app.features.transaction;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.features.transaction.TransactionAgreementInteractor;
import com.martins.valet.domain.features.transaction.TransactionCloseInteractor;
import com.martins.valet.domain.features.transaction.TransactionFindInteractor;
import com.martins.valet.domain.features.transaction.TransactionFindPlateInteractor;
import com.martins.valet.domain.features.transaction.TransactionListBrandsInteractor;
import com.martins.valet.domain.features.transaction.TransactionListTypeInteractor;
import com.martins.valet.domain.features.transaction.TransactionReopenInteractor;
import com.martins.valet.domain.features.transaction.TranscationSaveInteractor;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 7/7/16.
 */
@Module
public class TransactionModule {

    @Provides
    @PerActivity
    @Named("printer")
    UseCase providePrinterInteractor(PrinterInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("saveTransaction")
    UseCase provideSaveTransactionInteractor(TranscationSaveInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("findPlate")
    UseCase provideFindPlateTransactionInteractor(TransactionFindPlateInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("brands")
    UseCase provideListBrandInteractor(TransactionListBrandsInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("type")
    UseCase provideListTypeInteractor(TransactionListTypeInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("findTransaction")
    UseCase provideFindTransactionInteractor(TransactionFindInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("closeTransaction")
    UseCase provideCloseTransacationInteractor(TransactionCloseInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("agreement")
    UseCase provideAgreementTransactionInteractor(TransactionAgreementInteractor interactor){
        return interactor;
    }

    @Provides
    @PerActivity
    @Named("reopenTransaction")
    UseCase provideReopenTransactionInteractor(TransactionReopenInteractor interactor){
        return interactor;
    }

}
