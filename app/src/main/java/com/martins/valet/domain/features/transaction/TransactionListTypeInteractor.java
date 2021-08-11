package com.martins.valet.domain.features.transaction;

import com.martins.valet.domain.features.configuration.ConfigurationRepository;
import com.martins.valet.domain.features.model.TypeVehycle;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/16/16.
 */
public class TransactionListTypeInteractor extends UseCase<List<TypeVehycle>> {

    private final ConfigurationRepository repository;

    @Inject
    public TransactionListTypeInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, ConfigurationRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected void validate() {

    }

    @Override
    protected Observable<List<TypeVehycle>> useCaseResult() {
        return repository.getAllTypes();
    }
}
