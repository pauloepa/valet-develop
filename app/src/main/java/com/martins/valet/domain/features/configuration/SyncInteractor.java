package com.martins.valet.domain.features.configuration;

import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/16/16.
 */
public class SyncInteractor extends UseCase<Boolean> {

    private final ConfigurationRepository repository;

    @Inject public SyncInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, ConfigurationRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected void validate() {
        //nothing
    }

    @Override
    protected Observable<Boolean> useCaseResult() {
        return repository.sync();
    }
}
