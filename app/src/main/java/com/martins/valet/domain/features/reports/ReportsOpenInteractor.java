package com.martins.valet.domain.features.reports;

import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.transaction.TransactionRepository;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/31/16.
 */
public class ReportsOpenInteractor extends UseCase<List<Transaction>> {

    private final TransactionRepository repository;

    @Inject public ReportsOpenInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, TransactionRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected void validate() {

    }

    @Override
    protected Observable<List<Transaction>> useCaseResult() {
        return repository.transactionOpen();
    }
}
