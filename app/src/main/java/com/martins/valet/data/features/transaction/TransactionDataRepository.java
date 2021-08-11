package com.martins.valet.data.features.transaction;

import com.martins.valet.data.features.transaction.cloud.TransactionDataSourceCloud;
import com.martins.valet.data.features.transaction.disk.TransactionDataSourceDisk;
import com.martins.valet.domain.features.model.Agreement;
import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.domain.features.transaction.TransactionRepository;
import com.martins.valet.presentation.features.configuration.ConfigManager;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/13/16.
 */
public class TransactionDataRepository implements TransactionRepository {

    private final ConfigManager configManager;
    private final TransactionDataSourceCloud sourceCloud;
    private final TransactionDataSourceDisk sourceDisk;

    @Inject
    public TransactionDataRepository(ConfigManager configManager, TransactionDataSourceCloud transactionDataSourceCloud, TransactionDataSourceDisk transactionDataSourceDisk) {
        this.sourceCloud = transactionDataSourceCloud;
        this.sourceDisk = transactionDataSourceDisk;
        this.configManager = configManager;
    }

    @Override
    public Observable<Transaction> saveTransaction(final Transaction transaction) {
        return sourceCloud.createTransaction(configManager.getConfiguration().getUserToken(), transaction);
//        return sourceDisk.createTransaction(transaction);
    }

    @Override
    public Observable<VehycleInfo> findVehycleInfoFromPlate(String plate) {
        return sourceCloud.findVehycleInfoFromPlate(configManager.getConfiguration().getUserToken(), plate);
//        return sourceDisk.findVehycleInfoFromPlate(plate);
    }

    @Override
    public Observable<Transaction> findTransactionFromPlate(String plate, String agreement) {
//        return sourceDisk.findTransactionFromPlate(plate);
        return sourceCloud.findTransactionInfoFromPlateOrID(configManager.getConfiguration().getUserToken(), plate, agreement);
    }

    @Override
    public Observable<Transaction> closeTransaction(Transaction transaction) {
        return sourceCloud.closeTransaction(configManager.getConfiguration().getUserToken(), transaction);
//        return sourceDisk.closeTransaction(transaction);
    }

    @Override
    public Observable<List<Transaction>> transactionOpen() {
        return sourceCloud.getTransactionOpen(configManager.getConfiguration().getUserToken());
    }

    @Override
    public Observable<List<Agreement>> getAgreements() {
        return sourceCloud.getAgreements(configManager.getConfiguration().getUserToken());
    }

    @Override
    public Observable<Transaction> reopenTransaction(Transaction transaction) {
        return sourceCloud.reopenTransaction(configManager.getConfiguration().getUserToken(), transaction);
    }

    @Override
    public Observable<ReportsRevenuew> getReportsRevenuew(Date dateIn, Date dateOut) {
        return sourceCloud.getReportRevenuew(configManager.getConfiguration().getUserToken(), dateIn, dateOut);
    }
}
