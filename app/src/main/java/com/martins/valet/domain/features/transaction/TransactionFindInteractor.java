package com.martins.valet.domain.features.transaction;

import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/16/16.
 */
public class TransactionFindInteractor extends UseCase<Transaction> {

    private final TransactionRepository repository;

    private String plate;
    private String aggrement;

    @Inject public TransactionFindInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, TransactionRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected void validate() {
        if (plate == null){
            throw new IllegalArgumentException("Plate is not defined");
        }
    }

    @Override
    protected Observable<Transaction> useCaseResult() {
        return repository.findTransactionFromPlate(this.plate, this.aggrement);
    }

    public void setPlate(String plate, String aggrement) {
        this.plate = plate;
        this.aggrement = aggrement;
    }
}
