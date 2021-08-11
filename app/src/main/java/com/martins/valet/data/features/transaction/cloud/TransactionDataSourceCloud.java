package com.martins.valet.data.features.transaction.cloud;

import com.martins.valet.domain.features.model.Agreement;
import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.VehycleInfo;

import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by policante on 7/13/16.
 */
public interface TransactionDataSourceCloud {

    // In
    Observable<VehycleInfo> findVehycleInfoFromPlate(String token, String plate);

    Observable<Transaction> createTransaction(String token, Transaction transaction);

    //Out
    Observable<Transaction> findTransactionInfoFromPlateOrID(String token, String plateOrId, String convenio);

    Observable<Transaction> closeTransaction(String token, Transaction transaction);

    //Reports
    Observable<List<Transaction>> getTransactionOpen(String token);

    Observable<List<Agreement>> getAgreements(String token);

    Observable<Transaction> reopenTransaction(String token, Transaction transaction);

    Observable<ReportsRevenuew> getReportRevenuew(String token, Date dateIn, Date dateOut);
}
