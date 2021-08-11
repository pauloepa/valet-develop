package com.martins.valet.domain.features.transaction;

import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/11/16.
 */
public class TranscationSaveInteractor extends UseCase<Transaction> {

    private final TransactionRepository repository;

    private Transaction transaction;

    @Inject public TranscationSaveInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, TransactionRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    protected void validate() {
        if (this.transaction == null){
            throw new IllegalArgumentException("Transaction is not defined");
        }
        if (this.transaction.getVehycle() == null) {
            throw new IllegalArgumentException("Vehycle is not defined");
        }
    }

    @Override
    protected Observable<Transaction> useCaseResult() {
        return repository.saveTransaction(this.transaction);
    }

}
