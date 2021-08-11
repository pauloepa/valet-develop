package com.martins.valet.domain.features.transaction;

import com.martins.valet.domain.features.model.Agreement;
import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.VehycleInfo;

import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by policante on 7/11/16.
 */
public interface TransactionRepository {

    Observable<Transaction> saveTransaction(Transaction transaction);

    Observable<VehycleInfo> findVehycleInfoFromPlate(String plate);

    Observable<Transaction> findTransactionFromPlate(String plate, String agreement);

    Observable<Transaction> closeTransaction(Transaction transaction);

    Observable<List<Transaction>> transactionOpen();

    Observable<List<Agreement>> getAgreements();

    Observable<Transaction> reopenTransaction(Transaction transaction);

    Observable<ReportsRevenuew> getReportsRevenuew(Date dateIn, Date dateOut);
}
