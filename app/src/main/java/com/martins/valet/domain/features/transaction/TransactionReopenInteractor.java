package com.martins.valet.domain.features.transaction;

import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 8/6/16.
 */
public class TransactionReopenInteractor extends UseCase<Transaction> {

    private final TransactionRepository repository;

    private Transaction transaction;

    @Inject public TransactionReopenInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, TransactionRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected void validate() {
        if (transaction == null){
            throw new IllegalArgumentException("Transaction is not defined");
        }
    }

    @Override
    protected Observable<Transaction> useCaseResult() {
        return repository.reopenTransaction(transaction);
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
