package com.martins.valet.domain.features.transaction;

import com.martins.valet.domain.features.model.Agreement;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 8/4/16.
 */
public class TransactionAgreementInteractor extends UseCase<List<Agreement>> {

    private final TransactionRepository repository;

    @Inject public TransactionAgreementInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, TransactionRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected void validate() {

    }

    @Override
    protected Observable<List<Agreement>> useCaseResult() {
        return repository.getAgreements();
    }
}
