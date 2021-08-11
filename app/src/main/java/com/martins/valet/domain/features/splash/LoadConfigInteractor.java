package com.martins.valet.domain.features.splash;

import com.martins.valet.domain.features.configuration.ConfigurationRepository;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/18/16.
 */
public class LoadConfigInteractor extends UseCase<Configuration> {

    private final ConfigurationRepository repository;

    @Inject public LoadConfigInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, ConfigurationRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    @Override
    protected void validate() {

    }

    @Override
    protected Observable<Configuration> useCaseResult() {
        return this.repository.getConfiguration();
    }
}
